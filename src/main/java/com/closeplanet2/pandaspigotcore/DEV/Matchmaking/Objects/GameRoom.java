package com.closeplanet2.pandaspigotcore.DEV.Matchmaking.Objects;

import com.closeplanet2.pandaspigotcore.DEV.Matchmaking.Enums.GameState;
import com.closeplanet2.pandaspigotcore.DEV.Matchmaking.Enums.PlayerState;
import com.closeplanet2.pandaspigotcore.FINAL.World.WorldAPI;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

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

    public GameRoom(String gameName, String worldName, GameSettings gameSettings){
        gameWorld = WorldAPI.CreateNewInstanceOfWorld(worldName, gameName);
        this.gameSettings = gameSettings;
    }

    public GamePlayer ReturnGamePlayer(Player player){
        for(GamePlayer gamePlayer : playerStates.keySet()){
            if(gamePlayer.IsPlayer(player)) return gamePlayer;
        }
        return null;
    }

    public void SetLobbyLocation(Location lobbyLocation){
        this.lobbyLocation = lobbyLocation;
    }

    public void SetSpawnPoints(List<Location> spawnPoints){
        this.spawnPoints = spawnPoints;
    }

    public void AddCustomData(String key, Object data){
        customData.put(key, data);
    }

    public void SetGameState(GameState gameState){
        this.gameState = gameState;
    }

    public void AddPlayer(GamePlayer gamePlayer){
        playerStates.put(gamePlayer, PlayerState.Joining);
    }

    public void SetPlayerState(Player player, PlayerState playerState){
        var gamePlayer = ReturnGamePlayer(player);
        if(gamePlayer == null) return;
        playerStates.put(gamePlayer, playerState);
    }

    public void SetPlayerState(GamePlayer gamePlayer, PlayerState playerState){
        playerStates.put(gamePlayer, playerState);
    }

}
