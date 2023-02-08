package com.closeplanet2.pandaspigotcore.Location;

import com.closeplanet2.pandaspigotcore.Location.Enums.TIME_OF_DAY;
import com.closeplanet2.pandaspigotcore.Location.Objects.CustomLocation;
import com.closeplanet2.pandaspigotcore.Location.Objects.Vector3;
import com.closeplanet2.pandaspigotcore.PandaSpigotCore;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LocationAPI {
    public static void StoreTeleportLocation(Object name, World location){
        StoreTeleportLocation(name, location.getSpawnLocation());
    }

    public static void StoreTeleportLocation(Object name, Location location){
        PandaSpigotCore.INSTANCE.teleportLocations.put(name.toString(), location);
    }

    public static void TimeLockWorld(World world, TIME_OF_DAY time_of_day){
        PandaSpigotCore.INSTANCE.timeLockWorlds.put(world.getUID(), time_of_day);
    }

    public static Location RETURN(Object name){
        return PandaSpigotCore.INSTANCE.teleportLocations.getOrDefault(name.toString(), null);
    }

    public static void TELEPORT(Object name, Player player){
        var location = RETURN(name);
        if(location == null) return;
        player.teleport(location);
    }

    public static void TELEPORT(Object name, Entity player){
        var location = RETURN(name);
        if(location == null) return;
        player.teleport(location);
    }

    public static Vector3 ReturnMidPoint(Vector3 a, Vector3 b){
        var x1 = a.x;
        var x2 = b.x;
        var z1 = a.z;
        var z2 = b.z;
        var midX = (x1 + x2) / 2;
        var midZ = (z1 + z2) / 2;
        return new Vector3(midX, a.y, midZ);
    }
}
