package com.closeplanet2.pandaspigotcore.FINAL.Events;

import com.closeplanet2.pandaspigotcore.FINAL.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.FINAL.JavaClass.JavaClassAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class EventsAPI {
    public static void RegisterALlEvents(JavaPlugin javaPlugin, String path){
        try {
            List<Class<?>> eventClasses = JavaClassAPI.ReturnALlEventClasses(javaPlugin, path);
            if(eventClasses.isEmpty()) return;
            ConsoleCore.Send("");
            ConsoleCore.Send(ChatColor.AQUA + "==========[Register Event]==========");
            RegisterEvents(eventClasses, javaPlugin);
            ConsoleCore.Send(ChatColor.AQUA + "=======================================");
        } catch (Exception exception) {
            ConsoleCore.SendError(exception.getMessage());
        }
    }

    private static void RegisterEvents(List<Class<?>> classes, JavaPlugin plugin){
        for(var clazz : classes){
            Listener listener;
            try {
                listener = (Listener) clazz.getConstructor(plugin.getClass()).newInstance(plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                try {
                    listener = (Listener) clazz.getConstructor(JavaPlugin.class).newInstance(plugin);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev1) {
                    try {
                        listener = (Listener) clazz.getConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ev) {
                        ev.printStackTrace();
                        return;
                    }
                }
            }
            Bukkit.getPluginManager().registerEvents(listener, plugin);
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + plugin.getDescription().getFullName() + ": Registered listener " + listener.getClass().getName());
        }
    }
}
