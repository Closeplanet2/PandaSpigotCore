package com.closeplanet2.pandaspigotcore.World;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WorldAPI {
    public static World CreateNewInstanceOfWorld(String sourceWorldName, String targetWorldName){
        var rootDirectory = Bukkit.getServer().getWorldContainer().getAbsolutePath();
        return CreateNewInstanceOfWorld(new File(rootDirectory + "/" + sourceWorldName), new File(rootDirectory + "/" + targetWorldName), targetWorldName);
    }

    public static World CreateNewInstanceOfWorld(File source, File target, String targetWorldName){
        DeleteWorld(target);
        CopyWorld(source, target);
        return Bukkit.createWorld(new WorldCreator(targetWorldName));
    }

    public static void UnloadWorld(String worldName){
        UnloadWorld(Bukkit.getWorld(worldName));
    }

    public static void UnloadWorld(World world){
        if(world != null) Bukkit.getServer().unloadWorld(world, true);
    }

    public static boolean DeleteWorld(String worldName){
        return DeleteWorld(Bukkit.getWorld(worldName));
    }

    public static boolean DeleteWorld(World world){
        if(world == null) return false;
        return DeleteWorld(world.getWorldFolder());
    }

    public static boolean DeleteWorld(File path){
        if(path.exists()){
            File files[] = path.listFiles();
            for(var i = 0; i < files.length; i++){
                if(files[i].isDirectory()) DeleteWorld(files[i]);
                else files[i].delete();
            }
        }
        return(path.delete());
    }

    public static void CopyWorld(String source, String target){
        CopyWorld(Bukkit.getWorld(source), Bukkit.getWorld(target));
    }

    public static void CopyWorld(World source, World target){
        if(source == null || target == null) return;
        CopyWorld(source.getWorldFolder(), target.getWorldFolder());
    }

    public static void CopyWorld(File source, File target){
        try {
            ArrayList<String> ignore = new ArrayList<String>(Arrays.asList("uid.dat", "session.dat"));
            if(!ignore.contains(source.getName())) {
                if(source.isDirectory()) {
                    if(!target.exists())
                        target.mkdirs();
                    String files[] = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        CopyWorld(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) { }
    }
}
