package com.closeplanet2.pandaspigotcore.Matchmaking.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameTeam {
    private List<Player> playersOnTeam = new ArrayList<>();

    public GameTeam(Player player){
        playersOnTeam.add(player);
    }

    public GameTeam(List<Player> playersOnTeam){
        this.playersOnTeam = playersOnTeam;
    }

    public boolean IsPlayer(Player player){
        return playersOnTeam.contains(player);
    }

    public void AddPlayer(Player player){
        if(!playersOnTeam.contains(player)) playersOnTeam.add(player);
    }

    public void RemovePlayer(Player player){
        playersOnTeam.remove(player);
    }

    public void SendMessage(String message){
        for(var player : playersOnTeam) player.sendMessage(message);
    }

    public void Teleport(Location location){
        for(var player : playersOnTeam) player.teleport(location);
    }
}
