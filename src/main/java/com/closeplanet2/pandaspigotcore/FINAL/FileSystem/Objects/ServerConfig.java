package com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Objects;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.FileAndDirectory.FileCore;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ServerConfig {
    private File file;
    private FileConfiguration fileConfiguration;
    public boolean saveFlag;

    public ServerConfig(String dirPath, String fileName){
        saveFlag = FileCore.Exist(dirPath, fileName, ".yml");
        file = FileCore.Create(dirPath, fileName, ".yml");
        if(file != null) fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void set(String path, Object value, boolean override){
        if(fileConfiguration.contains(path) && !override) return;
        fileConfiguration.set(path, value);
    }

    public Object get(String path){
        return fileConfiguration.get(path);
    }

    public Object Return(String path){
        return fileConfiguration.get(path);
    }

    public ConfigurationSection getConfigurationSection(String path){
        return fileConfiguration.getConfigurationSection(path);
    }
    public boolean isConfigurationSection(String path){return fileConfiguration.isConfigurationSection(path);}

    public void SaveConfig(){
        try { fileConfiguration.save(file); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void DeleteConfig(){
        file.delete();
    }

    public void UpdateConfig(){
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }
}
