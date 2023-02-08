package com.closeplanet2.pandaspigotcore.JavaClass;

import com.closeplanet2.pandaspigotcore.Commands.Version2.Interfaces.PandaCommand;
import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.JavaClass.Enums.SoftDependPlugins;
import com.closeplanet2.pandaspigotcore.Loop.PandaLoop;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

public class JavaClassAPI {
    public static boolean IsPluginInstalled(SoftDependPlugins softDependPlugin){
        return Bukkit.getServer().getPluginManager().getPlugin(softDependPlugin.name()) != null;
    }

    public static List<Class<?>> ReturnALlCommandClasses(JavaPlugin javaPlugin, String path) throws IOException, URISyntaxException {
        return ReturnAllClasses(javaPlugin, path).stream().filter(clazz -> clazz.isAnnotationPresent(PandaCommand.class)).collect(Collectors.toList());
    }

    public static List<Class<?>> ReturnALlEventClasses(JavaPlugin javaPlugin, String path) throws IOException, URISyntaxException {
        return ReturnAllClasses(javaPlugin, path).stream().filter(clazz -> clazz.isAnnotationPresent(PandaEvent.class)).collect(Collectors.toList());
    }

    public static List<Class<?>> ReturnALlLoopClasses(JavaPlugin javaPlugin, String path) throws IOException, URISyntaxException {
        return ReturnAllClasses(javaPlugin, path).stream().filter(clazz -> clazz.isAnnotationPresent(PandaLoop.class)).collect(Collectors.toList());
    }

    private static List<Class<?>> ReturnAllClasses(JavaPlugin javaPlugin, String path) throws URISyntaxException, IOException {
        return ReturnClassNames(javaPlugin, path).stream().map(className -> {
            try {
                return Class.forName(className);
            } catch (Exception e) {
                ConsoleCore.SendError("Class: " + className + " does not exist, not loading.");
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private static List<String> ReturnClassNames(JavaPlugin javaPlugin, String path) throws URISyntaxException, IOException{
        var fileDir = new File(javaPlugin.getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
        var zip = new ZipInputStream(new FileInputStream(fileDir));
        var classNames = new ArrayList<String>();
        var entry = zip.getNextEntry();
        while(entry != null){
            if(!entry.isDirectory() && entry.getName().endsWith(".class") && !entry.getName().contains("$")){
                var className = entry.getName().replace('/', '.').replace(".class", "");
                if(className.contains(path)){
                    classNames.add(className);
                }
            }
            entry = zip.getNextEntry();
        }
        return classNames;
    }
}
