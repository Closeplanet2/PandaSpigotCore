package com.closeplanet2.pandaspigotcore.FINAL.Entity;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.List;

public class EntityAPI {
    public static List<Entity> ReturnAllEntities(Player player){ return ReturnAllEntities(player.getWorld()); }
    public static List<Entity> ReturnAllEntities(Location location){ return ReturnAllEntities(location.getWorld()); }
    public static List<Entity> ReturnAllEntities(World world){ return world.getEntities(); }

    public static List<Villager> ReturnAllVillagers(Player player){ return ReturnAllVillagers(player.getWorld()); }
    public static List<Villager> ReturnAllVillagers(Location location){ return ReturnAllVillagers(location.getWorld()); }
    public static List<Villager> ReturnAllVillagers(World world){
        var data = new ArrayList<Villager>();
        for(var entity : ReturnAllEntities(world)) if(entity instanceof Villager) data.add((Villager) entity);
        return data;
    }
}
