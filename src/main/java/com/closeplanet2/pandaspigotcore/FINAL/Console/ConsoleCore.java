package com.closeplanet2.pandaspigotcore.FINAL.Console;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ConsoleCore {
    private static final String PREFIX = "&c[&bCONSOLE LOG&c]:&f ";
    public static void Send(String message){
        var colorMessage = ChatColor.translateAlternateColorCodes('&', PREFIX + message);
        Bukkit.getConsoleSender().sendMessage(colorMessage);
    }
    public static void Send(Object message){
        if(message == null) return;
        Send(message.toString());
    }
    public static void SendError(String message){
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "===========================");
        Bukkit.getConsoleSender().sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + message);
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "===========================");
    }
    public static void SendError(Object message){
        SendError(message.toString());
    }
}
