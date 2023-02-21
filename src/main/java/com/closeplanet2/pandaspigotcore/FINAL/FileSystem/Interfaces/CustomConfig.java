package com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Enums.SaveType;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.FileSystemAPI;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Objects.ServerConfig;

public interface CustomConfig {
    String ReturnConfigID();
    Class<?> ReturnClassType();
    default String ReturnConfigPath(){ return "plugins/Configs/" + ReturnClassName() + "/";}
    default String ReturnClassName(){ return ReturnClassType().getSimpleName(); }
    default void SaveConfig(){ FileSystemAPI.Save(this, SaveType.PLUGIN_CONFIG); }
    default void LoadConfig(){ FileSystemAPI.Load(this, SaveType.PLUGIN_CONFIG); }
    default ServerConfig ReturnServerConfig(){
        var serverConfig = new ServerConfig(ReturnConfigPath(), ReturnConfigID());
        if(!serverConfig.saveFlag) SaveConfig();
        return serverConfig;
    }
}
