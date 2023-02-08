package com.closeplanet2.pandaspigotcore.ExternalAPIS;

import com.closeplanet2.pandaspigotcore.JavaClass.Enums.SoftDependPlugins;
import com.closeplanet2.pandaspigotcore.JavaClass.JavaClassAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.trait.GameModeTrait;
import net.citizensnpcs.trait.HomeTrait;
import net.citizensnpcs.trait.LookClose;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class CitizensAPI {
    public static final class NPCCreator{
        private String npcName = "";
        private EntityType entityType = EntityType.PLAYER;
        private boolean protectEntity = false;
        private Location spawnLocation;
        private boolean lookClose = false;
        private float walkSpeed = 0.2f;
        private String textureSignature = "";
        private String textureDetail = "";
        private GameMode gameMode = GameMode.CREATIVE;
        private Location homeLocation;
        private HashMap<Equipment.EquipmentSlot, ItemStack> npcInventory = new HashMap<>();

        public NPCCreator(){}

        public NPCCreator npcName(String npcName){
            this.npcName = npcName;
            return this;
        }

        public NPCCreator entityType(EntityType entityType){
            this.entityType = entityType;
            return this;
        }

        public NPCCreator protectEntity(boolean protectEntity){
            this.protectEntity = protectEntity;
            return this;
        }

        public NPCCreator spawnLocation(Location spawnLocation){
            this.spawnLocation = spawnLocation;
            return this;
        }

        public NPCCreator lookClose(boolean lookClose){
            this.lookClose = lookClose;
            return this;
        }

        public NPCCreator walkSpeed(float walkSpeed){
            this.walkSpeed = walkSpeed;
            return this;
        }

        public NPCCreator textureSignature(String textureSignature){
            this.textureSignature = textureSignature;
            return this;
        }

        public NPCCreator textureDetail(String textureDetail){
            this.textureDetail = textureDetail;
            return this;
        }

        public NPCCreator addNpcInventory(Equipment.EquipmentSlot equipmentSlot, ItemStack itemStack){
            npcInventory.put(equipmentSlot, itemStack);
            return this;
        }

        public NPCCreator gameMode(GameMode gameMode){
            this.gameMode = gameMode;
            return this;
        }

        public NPCCreator homeLocation(Location homeLocation){
            this.homeLocation = homeLocation;
            return this;
        }

        public NPC Spawn(){
            if(!JavaClassAPI.IsPluginInstalled(SoftDependPlugins.Citizens)) return null;
            var currentNPC = net.citizensnpcs.api.CitizensAPI.getNPCRegistry().createNPC(entityType, npcName);
            currentNPC.getOrAddTrait(SkinTrait.class).setSkinPersistent(npcName.replace(" ", ""), textureSignature, textureDetail);
            currentNPC.getOrAddTrait(LookClose.class).lookClose(lookClose);
            currentNPC.getNavigator().getDefaultParameters().baseSpeed(walkSpeed);
            currentNPC.getOrAddTrait(GameModeTrait.class).setGameMode(gameMode);
            if(homeLocation != null) currentNPC.getOrAddTrait(HomeTrait.class).setHomeLocation(homeLocation);
            for(var equipmentSlot : npcInventory.keySet()) currentNPC.getOrAddTrait(Equipment.class).set(equipmentSlot, npcInventory.get(equipmentSlot));
            currentNPC.setProtected(protectEntity);
            currentNPC.spawn(spawnLocation);
            return currentNPC;
        }
    }
}
