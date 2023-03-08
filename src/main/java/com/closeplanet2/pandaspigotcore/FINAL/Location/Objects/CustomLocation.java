package com.closeplanet2.pandaspigotcore.FINAL.Location.Objects;

import com.closeplanet2.pandaspigotcore.FINAL.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomVariable;
import com.closeplanet2.pandaspigotcore.FINAL.Variables.DoubleArgument;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.UUID;

public class CustomLocation implements CustomVariable {

    public UUID worldID;
    public double x;
    public double y;
    public double z;
    public float pitch;
    public float yaw;

    public static CustomLocation ZERO(){return new CustomLocation(0, 0, 0, 0, 0);}

    public CustomLocation(){}
    public CustomLocation(Location location){
        worldID = location.getWorld().getUID();
        x = location.getX();
        y = location.getY();
        z = location.getZ();
        yaw = location.getYaw();
        pitch = location.getPitch();
    }

    public CustomLocation(double x, double y, double z, float pitch, float yaw){
        worldID = UUID.randomUUID();
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location getLocation(){ return new Location(Bukkit.getWorld(worldID), x, y, z, yaw, pitch); }

    public Double distance(CustomLocation other){
        Vector3 difference = new Vector3(x - other.x, y - other.y, z - other.z);
        return Math.sqrt(Math.pow(difference.x, 2f) + Math.pow(difference.y, 2f) + Math.pow(difference.z, 2f));
    }

    @Override
    public String toString() { return String.format("%s,%f,%f,%f,%f,%f", worldID.toString(), x, y, z, pitch, yaw); }

    @Override
    public String Return() {
        return toString();
    }

    @Override
    public Object Set(String data) {
        ConsoleCore.Send(data);
        var rdata = data.replace(" ", "").split(",");
        worldID = UUID.fromString(rdata[0]);
        x = Double.parseDouble(rdata[1]);
        y = Double.parseDouble(rdata[2]);
        z = Double.parseDouble(rdata[3]);
        pitch = Float.parseFloat(rdata[4]);
        yaw = Float.parseFloat(rdata[5]);
        return this;
    }
}
