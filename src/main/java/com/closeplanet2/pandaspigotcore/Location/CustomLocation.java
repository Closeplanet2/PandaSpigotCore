package com.closeplanet2.pandaspigotcore.Location;

import com.closeplanet2.pandaspigotcore.FileSystem.Interfaces.CustomVariable;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class CustomLocation implements CustomVariable {

    public Vector3 locationData;
    public Vector2 rotationalData;
    public UUID worldID;

    public CustomLocation(Location location){
        locationData = new Vector3(location.getX(), location.getY(), location.getZ());
        rotationalData = new Vector2(location.getYaw(), location.getPitch());
        worldID = location.getWorld().getUID();
    }

    public Location Location(){ return new Location(Bukkit.getWorld(worldID), locationData.x, locationData.y, locationData.z, rotationalData.X(), rotationalData.Z()); }

    @Override
    public String toString() { return locationData.toString() + ":" + rotationalData.toString() + ":(" + worldID.toString() + ")"; }

    @Override
    public String Return() {
        return toString();
    }

    @Override
    public Object Set(String data) {
        return this;
    }
}
