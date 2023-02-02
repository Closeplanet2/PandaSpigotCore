package com.closeplanet2.pandaspigotcore;

import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.Location.Vector3;
import com.closeplanet2.pandaspigotcore.Networking.Objects.GameRoom;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PandaSpigotCore extends JavaPlugin {
    public static HashMap<UUID, GameRoom> networkGames = new HashMap<>();
    public static List<UUID> banSendMessages = new ArrayList<>();
    public static List<UUID> banGetMessages = new ArrayList<>();
    public static List<UUID> banSendCommands = new ArrayList<>();
    public static HashMap<String, Integer> loopRegister = new HashMap<>();
}
