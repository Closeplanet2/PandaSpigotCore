package com.closeplanet2.pandaspigotcore.FileSystem.Helpers;

import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.FileSystem.FileSystemAPI;
import com.closeplanet2.pandaspigotcore.FileSystem.Interfaces.CustomClass;
import com.closeplanet2.pandaspigotcore.FileSystem.Interfaces.CustomConfig;
import com.closeplanet2.pandaspigotcore.FileSystem.Interfaces.CustomHashMap;
import com.closeplanet2.pandaspigotcore.FileSystem.Interfaces.CustomVariable;
import com.closeplanet2.pandaspigotcore.FileSystem.Objects.ServerConfig;
import com.closeplanet2.pandaspigotcore.Variables.VariableAPI;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Serializer {
    public static Object HandleLoadSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) {
        if(data instanceof CustomHashMap){
            return serverConfig.Return(currentPath);
        }else if(data instanceof CustomVariable){
            var x = serverConfig.Return(currentPath).toString();
            var customVar = (CustomVariable) data;
            return customVar.Set(x);
        }else{
            return serverConfig.Return(currentPath);
        }
    }

    public static void HandleSaveSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException {
        if(data instanceof CustomHashMap) {

        }else if(data instanceof CustomVariable){
            serverConfig.Set(currentPath, ((CustomVariable) data).Return(), true);
            serverConfig.SaveConfig();
        }else {
            serverConfig.Set(currentPath, field.get(customConfig != null ? customConfig : customClass), true);
            serverConfig.SaveConfig();
        }
    }
}
