package com.closeplanet2.pandaspigotcore.FileSystem.Interfaces;

import com.closeplanet2.pandaspigotcore.FileSystem.Enums.SaveType;
import com.closeplanet2.pandaspigotcore.FileSystem.FileSystemAPI;
import com.closeplanet2.pandaspigotcore.FileSystem.Objects.ServerConfig;

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
