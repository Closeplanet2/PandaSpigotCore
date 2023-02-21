package com.closeplanet2.pandaspigotcore.DEV.Matchmaking.Objects;

import com.closeplanet2.pandaspigotcore.FINAL.Location.Objects.CustomLocation;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class GamePlayer {
    private Player player = null;
    private GameTeam gameTeam = null;
    private Location currentSpawnPoint;

    public boolean isTeamGame(){ return gameTeam != null && player == null; }
    public Player getPlayer(){return player;}
    public GameTeam getTeam(){return gameTeam;}

    public GamePlayer(Player player){
        this.player = player;
    }
    public GamePlayer(GameTeam gameTeam){
        this.gameTeam = gameTeam;
    }

    public boolean IsPlayer(Player player){
        if(isTeamGame()) gameTeam.IsPlayer(player);
        return this.player == player;
    }

    public void SendMessage(String message){
        if(isTeamGame()) gameTeam.SendMessage(message);
        else player.sendMessage(message);
    }

    public void Teleport(Entity entity){
        Teleport(entity.getLocation());
    }

    public void Teleport(CustomLocation customLocation){
        Teleport(customLocation.getLocation());
    }

    public void Teleport(Location location){
        if(isTeamGame()) gameTeam.Teleport(location);
        else player.teleport(location);
    }
    public void SetCurrentSpawnPoint(Location currentSpawnPoint){
        this.currentSpawnPoint = currentSpawnPoint;
    }
}
