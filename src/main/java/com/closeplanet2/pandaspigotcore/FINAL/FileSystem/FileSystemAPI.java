package com.closeplanet2.pandaspigotcore.FINAL.FileSystem;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Enums.SaveType;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Helpers.SerializeConfiguration;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;

public class FileSystemAPI {
    public static void Load(CustomConfig customConfig, SaveType saveType){
        switch (saveType){
            case PLUGIN_CONFIG -> ConfigAPI.Load(customConfig);
        }
    }

    public static void Save(CustomConfig customConfig, SaveType saveType){
        switch (saveType){
            case PLUGIN_CONFIG -> ConfigAPI.Save(customConfig);
        }
    }

    private static class ConfigAPI{
        private static void Load(CustomConfig customConfig){
            var serverConfig = customConfig.ReturnServerConfig();
            if(!serverConfig.saveFlag){ Save(customConfig); }
            else{
                try { SerializeConfiguration.Load(serverConfig, customConfig, null, "");
                } catch (IllegalAccessException e) { e.printStackTrace(); }
            }
        }

        private static void Save(CustomConfig customConfig){
            var serverConfig = customConfig.ReturnServerConfig();
            try { SerializeConfiguration.Save(serverConfig, customConfig, null, "");
            } catch (IllegalAccessException e) { e.printStackTrace(); }
            serverConfig.UpdateConfig();
        }
    }
}