package com.closeplanet2.pandaspigotcore.FINAL.Items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ItemStackAPI {

    public static int CountItem(Player player, ItemStack itemStack){
        var count = 0;
        for(var item : player.getInventory().getContents()){
            if(item == null) continue;
            if(IsItemTheSame(item, itemStack)) count += item.getAmount();
        }
        return count;
    }

    public static boolean IsItemTheSame(ItemStack a, ItemStack b){
        return a.isSimilar(b);
    }

    public static final class ItemStackBuilder{
        private String itemName = "test";
        private Material itemMaterial = Material.DIAMOND_PICKAXE;
        private int itemAmount = 1;
        private List<String> itemLore = new ArrayList<>();
        private List<ItemFlag> itemFlags = new ArrayList<>();
        private boolean useItemAdder = false;
        private HashMap<String, String> nbtTags = new HashMap<>();
        private HashMap<Enchantment, Integer> enchantments = new HashMap<>();

        public ItemStackBuilder(){}

        public ItemStackBuilder itemName(String itemName){
            this.itemName = itemName;
            return this;
        }

        public ItemStackBuilder itemMaterial(Material itemMaterial){
            this.itemMaterial = itemMaterial;
            return this;
        }

        public ItemStackBuilder itemAmount(int itemAmount){
            this.itemAmount = itemAmount;
            return this;
        }

        public ItemStackBuilder itemLore(String... itemLore){
            this.itemLore.addAll(Arrays.asList(itemLore));
            return this;
        }

        public ItemStackBuilder itemFlags(ItemFlag... itemFlags){
            this.itemFlags.addAll(Arrays.asList(itemFlags));
            return this;
        }

        public ItemStackBuilder useItemAdder(boolean useItemAdder){
            this.useItemAdder = useItemAdder;
            return this;
        }

        public ItemStackBuilder addNBTTag(String key, String value){
            nbtTags.put(key, value);
            return this;
        }

        public ItemStackBuilder addEnchantment(Enchantment enchantment, Integer level){
            enchantments.put(enchantment, level);
            return this;
        }

        public ItemStack build(){
            ItemStack itemStack = new ItemStack(itemMaterial, itemAmount);
            var itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(itemName);
            itemMeta.setLore(itemLore);
            for(var enchantment : enchantments.keySet()) itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true);
            for(var itemFlag : itemFlags) itemMeta.addItemFlags(itemFlag);
            itemStack.setItemMeta(itemMeta);
            for(String key : nbtTags.keySet()) NBTAPI.AddNBT(itemStack, key, nbtTags.get(key));
            return itemStack;
        }
    }
}
