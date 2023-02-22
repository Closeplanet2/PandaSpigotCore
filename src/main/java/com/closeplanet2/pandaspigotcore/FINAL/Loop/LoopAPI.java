package com.closeplanet2.pandaspigotcore.FINAL.Loop;

import com.closeplanet2.pandaspigotcore.FINAL.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.FINAL.JavaClass.JavaClassAPI;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class LoopAPI {
    public static void RegisterAllLoops(JavaPlugin javaPlugin, String path){
        try {
            List<Class<?>> loopClasses = JavaClassAPI.ReturnALlLoopClasses(javaPlugin, path);
            if(loopClasses.isEmpty()) return;
            ConsoleCore.Send("");
            ConsoleCore.Send(ChatColor.AQUA + "==========[Register Loops]==========");
            RegisterLoops(loopClasses, javaPlugin);
            ConsoleCore.Send(ChatColor.AQUA + "=======================================");
        }catch (Exception exception) {
            ConsoleCore.SendError(exception.getMessage());
        }
    }

    private static void RegisterLoops(List<Class<?>> classes, JavaPlugin plugin){
        for(var clazz : classes){
            LoopValues loopValues;
            try {
                loopValues = (LoopValues) clazz.getConstructor(plugin.getClass()).newInstance(plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                try {
                    loopValues = (LoopValues) clazz.getConstructor(JavaPlugin.class).newInstance(plugin);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev1) {
                    try {
                        loopValues = (LoopValues) clazz.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev) {
                        ev.printStackTrace();
                        return;
                    }
                }
            }
            PandaSpigotCore.INSTANCE.loopRegister.put(loopValues.loopName(), loopValues.initLoop());
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + plugin.getDescription().getFullName() + ": Registered loop " + loopValues.getClass().getName());
        }
    }
}
