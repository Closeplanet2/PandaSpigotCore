package com.closeplanet2.pandaspigotcore.FileSystem.FileAndDirectory;

import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryCore {
    public static boolean Create(String directoryPath){ return Create(new File(directoryPath)); }
    public static boolean Create(File directory){ return directory.exists() || directory.mkdirs(); }
    public static List<File> ReturnAllDirectoryPaths(File directory, boolean loop){
        var data = new ArrayList<File>();
        for(var child : directory.listFiles()){
            if(child.isDirectory()){
                if(loop) data.addAll(ReturnAllDirectoryPaths(child, loop));
                else data.add(child);
            }
        }
        return data;
    }
    public static File ReturnFileFromDirectory(File directory, String fileName, String extension){
        for(var child : directory.listFiles()){ if(child.getName().equalsIgnoreCase(fileName + extension)) return child; }
        return null;
    }
}
