package com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Helpers;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomClass;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomVariable;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Objects.ServerConfig;
import com.closeplanet2.pandaspigotcore.FINAL.Variables.VariableAPI;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.UUID;

public class Serializer {
    public static Object HandleLoadSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException {
        if(data.getClass() == HashMap.class){
            return HandleHashMapLoad(field, currentPath, serverConfig, customConfig, customClass);
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

    private static Object HandleHashMapLoad(Field field, String path, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass) throws IllegalAccessException {
        var stringListType = (ParameterizedType) field.getGenericType();
        var firstType = (Class<?>) stringListType.getActualTypeArguments()[0];
        var secondType = (Class<?>) stringListType.getActualTypeArguments()[1];
        var map = (HashMap<Object, Object>) field.get(customConfig != null ? customConfig : customClass);

        if(serverConfig.isConfigurationSection(path)){
            for(var varName : serverConfig.getConfigurationSection(path).getKeys(false)){
                if(IsSecondTypeSaveable(map)){
                    map.put(ReturnDataAsType(varName), SerializeConfiguration.Load(serverConfig, customConfig, customClass, path + "." + varName));
                }else{
                    map.put(ReturnDataAsType(varName), serverConfig.get(path + "." + varName));
                }
            }
        }



        return map;
    }

    public static void HandleSaveSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException {
        if(data.getClass() == HashMap.class) {
            HandleHashMap(field, currentPath, serverConfig, customConfig, customClass);
        }else if(data instanceof CustomVariable){
            serverConfig.set(currentPath, ((CustomVariable) data).Return(), true);
            serverConfig.SaveConfig();
        } else if(data.getClass() == UUID.class){
            serverConfig.set(currentPath, field.get(customConfig != null ? customConfig : customClass).toString(), true);
            serverConfig.SaveConfig();
        }else {
            serverConfig.set(currentPath, field.get(customConfig != null ? customConfig : customClass), true);
            serverConfig.SaveConfig();
        }
    }

    private static void HandleHashMap(Field field, String path, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass) throws IllegalAccessException {
        var stringListType = (ParameterizedType) field.getGenericType();
        var firstType = (Class<?>) stringListType.getActualTypeArguments()[0];
        var secondType = (Class<?>) stringListType.getActualTypeArguments()[1];

        var map = (HashMap<Object, Object>) field.get(customConfig != null ? customConfig : customClass);
        if(map.isEmpty()) return;

        if(IsSecondTypeSaveable(map)){
            for(var key : map.keySet()){
                var firstTypeName = firstType == UUID.class ? UUID.fromString(key.toString()).toString() : key.toString();
                SerializeConfiguration.Save(serverConfig, customConfig, customClass, path + "." + firstTypeName + ".");
            }
        }else{
            for(var key  : map.keySet()){
                var firstTypeName = firstType == UUID.class ? UUID.fromString(key.toString()).toString() : key.toString();
                var secondTypeValue = secondType == UUID.class ? UUID.fromString(map.get(key).toString()).toString() : map.get(key);
                serverConfig.set(path + "." + firstTypeName, secondTypeValue, true);
            }
        }

        serverConfig.SaveConfig();
    }

    private static boolean IsSecondTypeSaveable(HashMap<Object, Object> data){
        for(var key : data.keySet()) return data.get(key) instanceof CustomClass;
        return false;
    }

    private static Object ReturnDataAsType(String name){
        var dataName = name.replace('#', '.');
        if(VariableAPI.VariableTests().get(Boolean.class).IsType(dataName))
            return VariableAPI.VariableTests().get(Boolean.class).ReturnFrom(dataName);
        if(VariableAPI.VariableTests().get(Character.class).IsType(dataName))
            return VariableAPI.VariableTests().get(Character.class).ReturnFrom(dataName);
        if(VariableAPI.VariableTests().get(Double.class).IsType(dataName))
            return VariableAPI.VariableTests().get(Double.class).ReturnFrom(dataName);
        if(VariableAPI.VariableTests().get(Float.class).IsType(dataName))
            return VariableAPI.VariableTests().get(Float.class).ReturnFrom(dataName);
        if(VariableAPI.VariableTests().get(Integer.class).IsType(dataName))
            return VariableAPI.VariableTests().get(Integer.class).ReturnFrom(dataName);
        if(VariableAPI.VariableTests().get(Long.class).IsType(dataName))
            return VariableAPI.VariableTests().get(Long.class).ReturnFrom(dataName);
        if(VariableAPI.VariableTests().get(UUID.class).IsType(dataName))
            return VariableAPI.VariableTests().get(UUID.class).ReturnFrom(dataName);
        else
            return dataName;
    }
}
