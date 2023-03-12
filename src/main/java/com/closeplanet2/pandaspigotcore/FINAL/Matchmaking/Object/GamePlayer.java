package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomClass;
import com.closeplanet2.pandaspigotcore.FINAL.Location.Objects.CustomLocation;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Enums.PlayerState;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GamePlayer{
    public Player player = null;
    public GameTeam gameTeam = null;
    public PlayerState playerState = PlayerState.Joining;
    public Location currentSpawnPoint;

    public GamePlayer(Player player){ this.player = player; }
    public GamePlayer(GameTeam gameTeam){ this.gameTeam = gameTeam; }

    public HashMap<Player, Double> ReturnDistanceToLocation(Location location){
        if(IsTeamGame()) return gameTeam.ReturnDistanceToLocation(location);
        var data = new HashMap<Player, Double>();
        data.put(player, player.getLocation().distance(location));
        return data;
    }

    public boolean IsTeamGame(){
        return gameTeam != null && player == null;
    }

    public boolean IsPlayer(Player player){
        if(IsTeamGame()) return gameTeam.IsPlayer(player);
        return this.player == player;
    }

    public boolean CanPlayerJoin(int numberPerTeam){
        if(IsTeamGame()) return gameTeam.CanPlayerJoin(numberPerTeam);
        else return player == null;
    }

    public void AddPlayer(Player player){
        if(!IsTeamGame()) return;
        gameTeam.AddPlayer(player);
    }

    public boolean RemovePlayer(Player player){
        if(!IsTeamGame()) return true;
        return gameTeam.RemovePlayer(player);
    }

    public boolean IsLastPlayer(){
        if(!IsTeamGame()) return true;
        return gameTeam.IsLastPlayer();
    }

    public void SendMessage(String message){
        if(IsTeamGame()) gameTeam.SendMessage(message);
        else player.sendMessage(message);
    }

    public void Teleport(CustomLocation customLocation){ Teleport(customLocation.getLocation());}
    public void Teleport(Location location){
        if(IsTeamGame()) gameTeam.Teleport(location);
        else player.teleport(location);
    }

    public void SetSpawnPoint(Location currentSpawnPoint){ this.currentSpawnPoint = currentSpawnPoint; }
}
