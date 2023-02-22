package com.closeplanet2.pandaspigotcore.FINAL.Location.Objects;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomVariable;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class CustomLocation implements CustomVariable {

    public UUID worldID;
    public double x;
    public double y;
    public double z;
    public float yaw;
    public float pitch;

    public CustomLocation(Location location){
        worldID = location.getWorld().getUID();
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        yaw = location.getYaw();
        pitch = location.getPitch();
    }

    public Location getLocation(){ return new Location(Bukkit.getWorld(worldID), x, y, z, yaw, pitch); }

    public Double distance(CustomLocation other){
        Vector3 difference = new Vector3(x - other.x, y - other.y, z - other.z);
        return Math.sqrt(Math.pow(difference.x, 2f) + Math.pow(difference.y, 2f) + Math.pow(difference.z, 2f));
    }

    @Override
    public String toString() { return String.format("%f, %f, %f, %f, %f", x, y, z, pitch, yaw); }

    @Override
    public String Return() {
        return toString();
    }

    @Override
    public Object Set(String data) {
        return this;
    }
}
