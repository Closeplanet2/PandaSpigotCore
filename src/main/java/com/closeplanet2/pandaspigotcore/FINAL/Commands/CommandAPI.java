package com.closeplanet2.pandaspigotcore.FINAL.Commands;

import com.closeplanet2.pandaspigotcore.FINAL.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.FINAL.JavaClass.JavaClassAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CommandAPI {
    public static void RegisterALlCommands(JavaPlugin javaPlugin, String path){
        try {
            List<Class<?>> commandClasses = JavaClassAPI.ReturnALlCommandClasses(javaPlugin, path);
            if(commandClasses.isEmpty()) return;
            ConsoleCore.Send("");
            ConsoleCore.Send(ChatColor.AQUA + "==========[Register Commands]==========");
            RegisterCommands(commandClasses, javaPlugin);
            ConsoleCore.Send(ChatColor.AQUA + "=======================================");
        } catch (Exception ignored) { }
    }

    private static void RegisterCommands(List<Class<?>> classes, JavaPlugin plugin) throws Exception{
        var commandMap = (CommandMap) getField(Bukkit.getServer().getClass(), "commandMap").get(Bukkit.getServer());
        for(var clazz : classes){
            var commandClass = (Class<? extends PlayerCommand>) clazz;
            PlayerCommand command;
            try {
                command = commandClass.getConstructor(plugin.getClass()).newInstance(plugin);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                try {
                    command = commandClass.getConstructor(JavaPlugin.class).newInstance(plugin);
                } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
                    try {
                        command = commandClass.getConstructor().newInstance();
                    } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex2) {
                        ex2.printStackTrace();
                        return;
                    }
                }
            }

            commandMap.register(plugin.getName(), command);
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + plugin.getDescription().getFullName() + ": Registered command " + command.getName());
        }
    }

    private static Field getField(Class<?> clazz, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}
