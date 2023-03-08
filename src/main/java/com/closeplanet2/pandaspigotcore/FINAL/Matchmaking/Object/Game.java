package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;
import com.closeplanet2.pandaspigotcore.FINAL.Location.Objects.CustomLocation;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Enums.GameState;
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

    public boolean TestGameSettings(String gameType, int perTeam){ return gameSettings.TestGameSettings(gameType, playerList.size(), perTeam); }
    public boolean IsTeamGame(){return gameSettings.perTeam() > 1;}

    public Game(String gameName, GameSettings gameSettings){
        gameUUID = UUID.randomUUID();
        this.gameName = gameName;
        this.gameSettings = gameSettings;
    }

    public boolean AddPlayer(Player player){
        for(var gamePlayer : playerList) if(gamePlayer.IsPlayer(player)) return false;
        if(!IsTeamGame()){
            playerList.add(new GamePlayer(player));
            return true;
        }

        for(var team : playerList){
            if(team.CanPlayerJoin(gameSettings.perTeam())){
                team.AddPlayer(player);
                return true;
            }
        }

        playerList.add(new GamePlayer(new GameTeam(player)));
        return true;
    }

    public void SendMessage(String message){
        for(var gamePlayer : playerList) gamePlayer.SendMessage(message);
    }

    public void Teleport(CustomLocation customLocation){ Teleport(customLocation.getLocation());}
    public void Teleport(Location location){
        for(var gamePlayer : playerList) gamePlayer.Teleport(location);
    }


}
