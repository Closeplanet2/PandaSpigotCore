package com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Helpers;

import com.closeplanet2.pandaspigotcore.FINAL.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomClass;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomVariable;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Objects.ServerConfig;
import com.closeplanet2.pandaspigotcore.FINAL.Variables.VariableAPI;
import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Serializer {
    public static Object HandleLoadSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        if(data.getClass() == HashMap.class){
            return HandleHashMapLoad(field, currentPath, serverConfig, customConfig, customClass);
        }else if(data instanceof CustomVariable) {
            var x = serverConfig.Return(currentPath).toString();
            var customVar = (CustomVariable) data;
            var setData = customVar.Set(x);
            return setData;
        }else if(data.getClass() == UUID.class){
            return UUID.fromString(serverConfig.Return(currentPath).toString());
        } else if(data.getClass() == ArrayList.class){
            var stringListType = (ParameterizedType) field.getGenericType();
            var dataTypeList = (Class<?>) stringListType.getActualTypeArguments()[0];
            var returndata = new ArrayList<Object>();

            var configSubPath = currentPath + "." + dataTypeList.getSimpleName();
            if(CustomClass.class.isAssignableFrom(dataTypeList)){
                if(serverConfig.isConfigurationSection(configSubPath)){
                    for(var varName : serverConfig.getConfigurationSection(configSubPath).getKeys(false)){
                        var classobject = SerializeConfiguration.Load(serverConfig,
                                null,
                                (CustomClass) dataTypeList.getDeclaredConstructor().newInstance(),
                                configSubPath + "." + varName);
                        returndata.add(classobject);
                    }
                }
            }else if(CustomVariable.class.isAssignableFrom(dataTypeList)){
                if(serverConfig.isConfigurationSection(configSubPath)){
                    for(var varName : serverConfig.getConfigurationSection(configSubPath).getKeys(false)){
                        var customVar = (CustomVariable) dataTypeList.getDeclaredConstructor().newInstance();
                        customVar.Set(serverConfig.Return(configSubPath + "." + varName).toString());
                        returndata.add(customVar);
                    }
                }
            }else{
                return serverConfig.get(configSubPath);
            }

            return returndata;
        }else{
            return serverConfig.Return(currentPath);
        }
    }

    private static Object HandleHashMapLoad(Field field, String path, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        var stringListType = (ParameterizedType) field.getGenericType();
        var firstType = (Class<?>) stringListType.getActualTypeArguments()[0];
        var secondType = (Class<?>) stringListType.getActualTypeArguments()[1];
        var map = (HashMap<Object, Object>) field.get(customConfig != null ? customConfig : customClass);

        if(serverConfig.isConfigurationSection(path)){
            for(var varName : serverConfig.getConfigurationSection(path).getKeys(false)){
                if(IsSecondTypeSaveable(map)){
                    map.put(ReturnDataAsType(varName), SerializeConfiguration.Load(serverConfig, null, (CustomClass) firstType.getDeclaredConstructor().newInstance(), path + "." + varName));
                }else{
                    map.put(ReturnDataAsType(varName), serverConfig.get(path + "." + varName));
                }
            }
        }

        return map;
    }

    //TODO for arraylist support other types List<HashMap<String, String>
    //TODO hashmap could include arraylist HashMap<String, List<Custom Class>>
    //TODO create a helper which passes in a bunch of objects and return string with everything formatted for path
    public static void HandleSaveSerialize(Object data, Field field, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if(data.getClass() == HashMap.class) {
            HandleHashMap(field, currentPath, serverConfig, customConfig, customClass);
        }else if(data instanceof CustomVariable){
            serverConfig.set(currentPath, ((CustomVariable) data).Return(), true);
            serverConfig.SaveConfig();
        } else if(data.getClass() == UUID.class) {
            serverConfig.set(currentPath, field.get(customConfig != null ? customConfig : customClass).toString(), true);
            serverConfig.SaveConfig();
        } else if(data.getClass() == ArrayList.class){
            var stringListType = (ParameterizedType) field.getGenericType();
            var dataTypeList = (Class<?>) stringListType.getActualTypeArguments()[0];
            var arrayList = (ArrayList<Object>) field.get(customConfig != null ? customConfig : customClass);

            if(CustomClass.class.isAssignableFrom(dataTypeList)){
                for(var i = 0; i < arrayList.size(); i++){
                    var arrayData = arrayList.get(i);
                    var path = currentPath + "." + arrayData.getClass().getSimpleName() +"." + i + ".";
                    SerializeConfiguration.Save(serverConfig, null, (CustomClass) arrayData, path);
                }
            }else if(CustomVariable.class.isAssignableFrom(dataTypeList)){
                for(var i = 0; i < arrayList.size(); i++){
                    var arrayData = arrayList.get(i);
                    var path = currentPath + "." + arrayData.getClass().getSimpleName() +"." + i;
                    serverConfig.set(path, ((CustomVariable) arrayData).Return(), true);
                    serverConfig.SaveConfig();
                }
            }else{
                serverConfig.set(currentPath + "." + dataTypeList.getSimpleName(), field.get(customConfig != null ? customConfig : customClass), true);
                serverConfig.SaveConfig();
            }
        }else {
            serverConfig.set(currentPath, field.get(customConfig != null ? customConfig : customClass), true);
            serverConfig.SaveConfig();
        }
    }

    private static void HandleHashMap(Field field, String path, ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        var stringListType = (ParameterizedType) field.getGenericType();
        var firstType = (Class<?>) stringListType.getActualTypeArguments()[0];
        var secondType = (Class<?>) stringListType.getActualTypeArguments()[1];

        var map = (HashMap<Object, Object>) field.get(customConfig != null ? customConfig : customClass);
        if(map.isEmpty()) return;

        if(IsSecondTypeSaveable(map)){
            for(var key : map.keySet()){
                var firstTypeName = (firstType == UUID.class ? UUID.fromString(key.toString()).toString() : key.toString()).replace(".", "_#_");
                SerializeConfiguration.Save(serverConfig, null, (CustomClass) map.get(key), path + "." + firstTypeName + ".");
            }
        }else{
            for(var key  : map.keySet()){
                var firstTypeName = (firstType == UUID.class ? UUID.fromString(key.toString()).toString() : key.toString()).replace(".", "_#_");
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
