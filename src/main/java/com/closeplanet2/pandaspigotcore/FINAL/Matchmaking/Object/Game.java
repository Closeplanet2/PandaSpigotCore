package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;
import com.closeplanet2.pandaspigotcore.FINAL.Location.Objects.CustomLocation;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Enums.GameState;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Enums.PlayerState;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.GameNetworkCallbacks;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Interface.GameSettingsObject;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Runnables.GameLobbyCountdown;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Runnables.PlayerLeaveCountdown;
import com.closeplanet2.pandaspigotcore.FINAL.MessageSystem.MessageAPI;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Game {
    public UUID gameUUID;
    public String gameName;
    public GameSettings gameSettings;

    public World gameWorld;
    public Location lobbyLocation;
    public HashMap<String, Object> customData = new HashMap<>();
    public List<Location> spawnPoints = new ArrayList<>();
    public List<GamePlayer> playerList = new ArrayList<>();
    public GameState gameState = GameState.STARTING;

    public GameLobbyCountdown gameLobbyCountdown;
    public HashMap<UUID, PlayerLeaveCountdown> playerCountdowns = new HashMap<>();

    public Game(String gameName, GameSettings gameSettings){
        gameUUID = UUID.randomUUID();
        this.gameName = gameName;
        this.gameSettings = gameSettings;
    }

    public void PassGameSettings(GameSettingsObject gameSettingsObject){
        gameWorld = gameSettingsObject.gameWorld(gameUUID.toString());
        lobbyLocation = gameSettingsObject.lobbyLocation(gameWorld);
        customData = gameSettingsObject.customData();
        spawnPoints = gameSettingsObject.spawnPoints(gameWorld);
        for(var npccreator : gameSettingsObject.citizensNPCS(gameWorld)){ npccreator.Spawn(); }
    }

    public Player ReturnClosestPlayerToLocation(Location location){
        var min = 100000.0;
        Player player = null;
        for(var gamePlayer : playerList){
            var returnData = gamePlayer.ReturnDistanceToLocation(location);
            for(var playerKey : returnData.keySet()){
                if(returnData.get(playerKey) < min){
                    player = playerKey;
                    min = returnData.get(playerKey);
                }
            }
        }
        return player;
    }
    public int ReturnTeamIndexOfPlayer(Player player){
        for(var i = 0; i < playerList.size(); i++){
            var gamePlayer = playerList.get(i);
            if(gamePlayer.IsPlayer(player)) return i;
        }
        return -1;
    }

    public boolean TestGameSettings(String gameType, int perTeam){ return gameSettings.TestGameSettings(gameType, playerList.size(), perTeam); }
    public boolean IsTeamGame(){return gameSettings.perTeam() > 1;}
    public boolean IsPlayerInGame(Player player){
        for(var gamePlayer : playerList) if(gamePlayer.IsPlayer(player)) return true;
        return false;
    }
    public boolean AddPlayer(Player player){
        for(var gamePlayer : playerList) if(gamePlayer.IsPlayer(player)) return false;
        if(!IsTeamGame()){
            var gamePlayer = new GamePlayer(player);
            gamePlayer.currentSpawnPoint = spawnPoints.get(playerList.size());
            playerList.add(gamePlayer);
            return true;
        }

        for(var team : playerList){
            if(team.CanPlayerJoin(gameSettings.perTeam())){
                team.AddPlayer(player);
                return true;
            }
        }

        var newPlayer = new GamePlayer(new GameTeam(player));
        newPlayer.currentSpawnPoint = spawnPoints.get(playerList.size());
        playerList.add(newPlayer);
        return true;
    }
    public boolean HasGameReachedMinPlayers(){return playerList.size() >= gameSettings.minPlayers();}
    public boolean IsLocationInWorld(Location location){
        if(location.getWorld() == null) return false;
        return location.getWorld().getUID() == gameWorld.getUID();
    }

    public void SendPlayerToLobby(Player player){
        player.teleport(lobbyLocation);
    }
    public void ChangePlayerStatus(Player player, PlayerState playerState){
        for(var gamePlayer : playerList){
            if(gamePlayer.IsPlayer(player)) gamePlayer.playerState = playerState;
        }
    }
    public void StartLobbyCountdown(GameNetworkCallbacks gameNetworkCallbacks, int time){
        gameLobbyCountdown = new GameLobbyCountdown(gameNetworkCallbacks, this, time);
        gameLobbyCountdown.runTaskTimer(PandaSpigotCore.INSTANCE, 0, 20);
        gameState = GameState.STARTING;
    }
    public void SendPlayersToGame(){
        for(var player : playerList) player.Teleport(player.currentSpawnPoint);
        gameState = GameState.ACTIVE;
    }
    public void RemovePlayer(GameNetworkCallbacks networkCallbacks, Player player, int timeToRejoin){
        GamePlayer gamePlayer = null;
        for(var gp : playerList) if(gp.IsPlayer(player)) gamePlayer = gp;
        if(gamePlayer == null) return;

        ChangePlayerStatus(player, PlayerState.Leaving);

        if(gameState == GameState.LOBBY || gameState == GameState.STARTING){
            if(!IsTeamGame()){
                playerList.remove(gamePlayer);
                CheckWhatPlayerLeaveCauses();
                return;
            }else{
                gamePlayer.RemovePlayer(player);
                CheckWhatPlayerLeaveCauses();
                return;
            }
        }else{
            if(gamePlayer.IsLastPlayer()){
                networkCallbacks.NoPlayerLeftInTeam(this, playerList.indexOf(gamePlayer));
                ChangePlayerStatus(player, PlayerState.Left);
                return;
            }
        }

        ChangePlayerStatus(player, PlayerState.Left);
        playerCountdowns.put(player.getUniqueId(), new PlayerLeaveCountdown(networkCallbacks, this, player, timeToRejoin));
        playerCountdowns.get(player.getUniqueId()).runTaskTimer(PandaSpigotCore.INSTANCE, 0, 20);
    }
    public void RemovePlayerOnTimeExpire(GameNetworkCallbacks gameNetworkCallbacks, Player player){
        GamePlayer gamePlayer = null;
        for(var gp : playerList) if(gp.IsPlayer(player)) gamePlayer = gp;
        if(gamePlayer == null) return;

        if(!IsTeamGame()){
            playerList.remove(gamePlayer);
        }else{
            if(!gamePlayer.RemovePlayer(player))
                gameNetworkCallbacks.NoPlayerLeftInTeam(this, playerList.indexOf(gamePlayer));
        }
    }
    private void CheckWhatPlayerLeaveCauses(){
        if(gameState == GameState.STARTING && !HasGameReachedMinPlayers()){
            gameLobbyCountdown.cancel();
            gameLobbyCountdown = null;
        }
    }

    public void SendMessage(Player player, String message){ for(var gamePlayer : playerList) if (gamePlayer.IsPlayer(player)) gamePlayer.SendMessage(message); }
    public void SendMessage(String message){ for(var gamePlayer : playerList) gamePlayer.SendMessage(message); }
    public void Teleport(CustomLocation customLocation) { Teleport(customLocation.getLocation()); }
    public void Teleport(Location location){
        for(var gamePlayer : playerList) gamePlayer.Teleport(location);
    }


}
