package com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Helpers;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomClass;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Objects.ServerConfig;

import java.lang.reflect.InvocationTargetException;

public class SerializeConfiguration {
    public static void Save(ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        for(var field: customConfig != null ? customConfig.getClass().getDeclaredFields() : customClass.getClass().getDeclaredFields()){
            var data = field.get(customConfig != null ? customConfig : customClass);
            if(data == null) continue;
            if(data instanceof CustomClass){
                Save(serverConfig, null, (CustomClass) data, currentPath.equals("") ? field.getName() : currentPath + "." + field.getName());
            }else{
                Serializer.HandleSaveSerialize(
                        data, field, serverConfig, customConfig, customClass, currentPath.equals("") ? field.getName() : currentPath + "." + field.getName()
                );
            }
        }
    }

    public static Object Load(ServerConfig serverConfig, CustomConfig customConfig, CustomClass customClass, String currentPath) throws IllegalAccessException {
        for(var field : customConfig != null ? customConfig.getClass().getDeclaredFields() : customClass.getClass().getDeclaredFields()){
            var data = field.get(customConfig != null ? customConfig : customClass);
            if(data == null) continue;
            if(data instanceof CustomClass){
                var path = currentPath.equals("") ? field.getName() : currentPath + "." + field.getName();
                field.set(customConfig != null ? customConfig : customClass, Load(serverConfig, null, (CustomClass) data, path));
            }else{
                var rData = Serializer.HandleLoadSerialize(
                        data, field, serverConfig, customConfig, customClass, currentPath.equals("") ? field.getName() : currentPath + "." + field.getName()
                );
                if(rData == null) continue;
                if(data.getClass() == Character.class) rData = rData.toString().charAt(0);
                if(data.getClass() == Float.class){
                    var sub = (double) rData;
                    rData = (float) sub;
                }
                if(data.getClass() == Long.class){
                    var sub = (int) rData;
                    rData = (long) sub;
                }
                field.set(customConfig != null ? customConfig : customClass, rData);
            }
        }
        return customConfig != null ? customConfig : customClass;
    }
}
