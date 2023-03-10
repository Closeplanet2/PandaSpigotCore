package com.closeplanet2.pandaspigotcore.FINAL.FileSystem.FileAndDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DirectoryCore {
    public static boolean Create(String directoryPath){ return Create(new File(directoryPath)); }
    public static boolean Create(File directory){ return directory.exists() || directory.mkdirs(); }
    public static List<File> ReturnAllDirectoryPaths(File directory, boolean loop){
        var data = new ArrayList<File>();
        for(var child : Objects.requireNonNull(directory.listFiles())){
            if(child.isDirectory()){
                if(loop) data.addAll(ReturnAllDirectoryPaths(child, loop));
                else data.add(child);
            }
        }
        return data;
    }
    public static File ReturnFileFromDirectory(File directory, String fileName, String extension){
        for(var child : Objects.requireNonNull(directory.listFiles())){ if(child.getName().equalsIgnoreCase(fileName + extension)) return child; }
        return null;
    }
    public static List<String> ReturnAllFileNamesFromDirectory(String directory){
        return ReturnAllFileNamesFromDirectory(new File(directory));
    }
    public static List<String> ReturnAllFileNamesFromDirectory(File directory){
        var data = new ArrayList<String>();
        for(var child : Objects.requireNonNull(directory.listFiles())){
            if(!child.isDirectory()) data.add(child.getName());
        }
        return data;
    }
}
