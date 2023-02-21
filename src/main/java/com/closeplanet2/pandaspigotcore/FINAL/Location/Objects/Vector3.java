package com.closeplanet2.pandaspigotcore.FINAL.Location.Objects;


import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomVariable;
import org.bukkit.Location;
import org.bukkit.World;

public class Vector3 implements CustomVariable {

    public float x;
    public float y;
    public float z;

    //Constructors
    public Vector3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(double x, double y, double z){
        this.x = (float) x;
        this.y = (float) y;
        this.z = (float) z;
    }

    public Vector3(Location location, boolean block){
        this.x = (float) (block ? location.getBlockX() : location.getX());
        this.y = (float) (block ? location.getBlockY() : location.getY());
        this.z = (float) (block ? location.getBlockZ() : location.getZ());
    }

    public Location CreateLocation(World world){
        return new Location(world, x , y, z);
    }

    //Mathmatical operations
    public Vector3 __add__(Vector3 vector3){ return new Vector3(x + vector3.x, y + vector3.y, z + vector3.z); }
    public Vector3 __sub__(Vector3 vector3){ return new Vector3(x - vector3.x, y - vector3.y, z - vector3.z); }
    public Vector3 __mul__(Vector3 vector3){ return new Vector3(x * vector3.x, y * vector3.y, z * vector3.z); }
    public Vector3 __truediv__(Vector3 vector3){ return new Vector3(x / vector3.x, y / vector3.y, z / vector3.z); }
    public Vector3 __floordiv__(Vector3 vector3){ return new Vector3(Math.floor(x / vector3.x), Math.floor(y / vector3.y), Math.floor(z / vector3.z)); }
    public Vector3 __mod__(Vector3 vector3){ return new Vector3(x % vector3.x, y % vector3.y, z % vector3.z); }
    public Vector3 __pow__(Vector3 vector3){ return new Vector3(Math.pow(x, vector3.x), Math.pow(y, vector3.y), Math.pow(z, vector3.z)); }


    //Static Constructors
    public static Vector3 back(){ return new Vector3(0, 0, -1); }
    public static Vector3 down(){ return new Vector3(0, -1, 0); }
    public static Vector3 forward(){ return new Vector3(0, 0, 1); }
    public static Vector3 left(){ return new Vector3(-1, 0, 0); }
    public static Vector3 right(){ return new Vector3(0, 1, 0); }
    public static Vector3 up(){ return new Vector3(0, 1, 0); }
    public static Vector3 one(){ return new Vector3(1, 1, 1); }
    public static Vector3 zero(){ return new Vector3(0 ,0, 0); }
    public static Vector3 at(float x, float y, float z){ return new Vector3(x, y, z); }

    //Static Functions
    public static double Angle(Vector3 a, Vector3 b){
        var dot = a.x * b.x + a.y * b.y + a.z * b.z;
        var theta = dot / (a.Magnitude() * b.Magnitude());
        return theta * (180 / Math.PI);
    }

    public static double Distance(Vector3 a, Vector3 b){
        var dx = a.x - b.x;
        var dy = a.y - b.y;
        var dz = a.z - b.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static Vector3 ClampMagnitude(Vector3 vector3, double maxLength){
        var mag = vector3.Magnitude();
        if( mag > maxLength){
            var scale = maxLength / mag;
            return new Vector3(vector3.x * scale, vector3.y * scale, vector3.z * scale);
        }
        return vector3;
    }

    public static Vector3 Cross(Vector3 a, Vector3 b){
        var x = a.y * b.z - a.z * b.y;
        var y = a.z * b.x - a.x * b.z;
        var z = a.x * b.y - a.y * b.x;
        return new Vector3(x, y, z);
    }

    public static Vector3 Lerp(Vector3 a, Vector3 b, double t){
        t = Math.max(0, Math.min(1, t));
        var x = a.x + (b.x - a.x) * t;
        var y = a.y + (b.y - a.y) * t;
        var z = a.z + (b.z = a.z) * t;
        return new Vector3(x, y, z);
    }

    public static Vector3 LerpUnclamped(Vector3 a, Vector3 b, double t){
        var x = a.x + (b.x - a.x) * t;
        var y = a.y + (b.y - a.y) * t;
        var z = a.z + (b.z = a.z) * t;
        return new Vector3(x, y, z);
    }

    public static Vector3 Max(Vector3 a, Vector3 b){
        var x = Math.max(a.x, b.x);
        var y = Math.max(a.y, b.y);
        var z = Math.max(a.z, b.z);
        return new Vector3(x, y, z);
    }

    public static Vector3 Min(Vector3 a, Vector3 b){
        var x = Math.min(a.x, b.x);
        var y = Math.min(a.y, b.y);
        var z = Math.min(a.z, b.z);
        return new Vector3(x, y, z);
    }

    public static Vector3 MoveTowards(Vector3 current, Vector3 target, double maxDistanceDelta){
        var toVector = new Vector3(target.x - current.x, target.y - current.y, target.z - current.z);
        var distance = Math.sqrt(toVector.x * toVector.x + toVector.y * toVector.y + toVector.z * toVector.z);
        if(distance <= maxDistanceDelta || distance == 0){
            return target;
        }
        var moveVector = new Vector3(toVector.x / distance, toVector.y / distance, toVector.z / distance);
        moveVector.x *= maxDistanceDelta;
        moveVector.y *= maxDistanceDelta;
        moveVector.z *= maxDistanceDelta;
        return new Vector3(current.x + moveVector.x, current.y + moveVector.y, current.z + moveVector.z);
    }

    public static Vector3 Normalize(Vector3 a){
        var mag = a.Magnitude();
        return mag > 0 ? new Vector3(a.x / mag, a.y / mag, a.z / mag) : Vector3.zero();
    }

    public static float Dot(Vector3 a, Vector3 b){
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }

    //Functions
    public Vector3 Normalized(){
        var mag = Magnitude();
        return mag > 0.00001 ? new Vector3(x / mag, y / mag, z / mag) : Vector3.zero();
    }

    public boolean Equals(Vector3 vector3){ return x == vector3.x && y == vector3.y && z == vector3.z; }
    public double Magnitude(){ return Math.sqrt(x*x + y*y + z*z); }
    public double SqrMagnitude(){ return x*x + y*y + z*z; }

    @Override
    public String toString() { return "(" + x + "," + y + "," + z + ")"; }

    @Override
    public String Return() {
        return toString();
    }

    @Override
    public Object Set(String data) {
        var split = data.replace("(", "").replace(")", "").split(",");
        return new Vector3(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]));
    }
}