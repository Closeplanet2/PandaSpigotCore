package com.closeplanet2.pandaspigotcore.Location.Objects;

import com.closeplanet2.pandaspigotcore.FileSystem.Interfaces.CustomVariable;
import org.bukkit.Location;

public class Vector2 implements CustomVariable {

    private float x;
    private float z;

    public Vector2(float x, float z){
        this.x = x;
        this.z = z;
    }

    public Vector2(double x, double z){
        this.x = (float) x;
        this.z = (float) z;
    }

    public Vector2(Location location, boolean block){
        this.x = (float) (block ? location.getBlockX() : location.getX());
        this.z = (float) (block ? location.getBlockZ() : location.getZ());
    }

    public float X(){ return x; }
    public float Z(){ return z; }
    public void SetX(float x){ this.x = x; }
    public void SetZ(float z){ this.z = z; }

    @Override
    public String toString() { return "(" + x + ", " + z + ")"; }

    @Override
    public String Return() {
        return toString();
    }

    @Override
    public Object Set(String data) {
        return this;
    }
}
