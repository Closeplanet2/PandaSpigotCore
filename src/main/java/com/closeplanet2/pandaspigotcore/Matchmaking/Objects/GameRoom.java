package com.closeplanet2.pandaspigotcore.Matchmaking.Objects;

import com.closeplanet2.pandaspigotcore.Matchmaking.Enums.GameState;
import com.closeplanet2.pandaspigotcore.Matchmaking.Enums.PlayerState;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.List;

public class GameRoom {

    private World gameWorld;
    private Location lobbyLocation;
    private List<Location> spawnPoints;
    private HashMap<GamePlayer, PlayerState> playerStates = new HashMap<>();
    private GameSettings gameSettings;
    private boolean isTeamGame;
    private HashMap<String, Object> customData = new HashMap<>();
    private GameState gameState = GameState.STARTING;

    public GameRoom(String gameName){

    }

}
