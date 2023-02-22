package com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Helpers;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomClass;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomHashMap;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomVariable;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Objects.ServerConfig;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.util.UUID;

public class Serializer {
    public static Object HandleLoadSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) {
        if(data instanceof CustomHashMap){

        }else if(data instanceof CustomVariable) {
            var x = serverConfig.Return(currentPath).toString();
            var customVar = (CustomVariable) data;
            return customVar.Set(x);
        }else if(data.getClass() == UUID.class){
            return UUID.fromString(serverConfig.Return(currentPath).toString());
        }else{
            return serverConfig.Return(currentPath);
        }
    }

    public static void HandleSaveSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException {
        if(data instanceof CustomHashMap) {

        }else if(data instanceof CustomVariable){
            serverConfig.Set(currentPath, ((CustomVariable) data).Return(), true);
            serverConfig.SaveConfig();
        } else if(data.getClass() == UUID.class){
            serverConfig.Set(currentPath, field.get(customConfig != null ? customConfig : customClass).toString(), true);
            serverConfig.SaveConfig();
        }else {
            serverConfig.Set(currentPath, field.get(customConfig != null ? customConfig : customClass), true);
            serverConfig.SaveConfig();
        }
    }
}
