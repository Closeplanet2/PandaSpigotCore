package com.closeplanet2.pandaspigotcore.FINAL;

import com.closeplanet2.pandaspigotcore.FINAL.JavaClass.JavaClassAPI;
import com.closeplanet2.pandaspigotcore.FINAL.Location.Enums.TIME_OF_DAY;
import com.closeplanet2.pandaspigotcore.DEV.Matchmaking.Objects.GameRoom;
import com.closeplanet2.pandaspigotcore.FINAL.Player.Enums.*;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class PandaSpigotCore extends JavaPlugin {
    public static PandaSpigotCore INSTANCE;
    public HashMap<UUID, GameRoom> networkGames = new HashMap<>();
    public HashMap<String, Integer> loopRegister = new HashMap<>();
    public HashMap<String, Location> teleportLocations = new HashMap<>();
    public HashMap<String, String> storedMessages = new HashMap<>();

    public HashMap<UUID, HashMap<String, Object>> playerDataRegister = new HashMap<>();
    public HashMap<String, Object> internalDataRegister = new HashMap<>();

    public HashMap<UUID, TIME_OF_DAY> timeLockWorlds = new HashMap<>();

    public HashMap<UUID, COMMAND_STATE> playerCommandStates = new HashMap<>();
    public HashMap<UUID, EXP_STATE> playerEXPStates = new HashMap<>();
    public HashMap<UUID, HEALTH_STATE> playerHealthStates = new HashMap<>();
    public HashMap<UUID, HUNGER_STATE> playerHungerStates = new HashMap<>();
    public HashMap<UUID, MESSAGE_STATE> playerMessageStates = new HashMap<>();
    public HashMap<UUID, MOVE_STATE> playerMoveStates = new HashMap<>();
    public HashMap<UUID, TELEPORT_STATE> playerTeleportStates = new HashMap<>();

    public HashMap<UUID, Location> STORE_PLAYER_LOCATION = new HashMap<>();

    @Override
    public void onEnable() {
        INSTANCE = this;
        JavaClassAPI.Register(this, "com.closeplanet2.pandaspigotcore");
    }
}

