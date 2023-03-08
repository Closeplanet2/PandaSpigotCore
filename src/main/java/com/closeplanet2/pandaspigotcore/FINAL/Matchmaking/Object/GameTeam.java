package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomClass;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameTeam  {
    public List<Player> playersOnTeam = new ArrayList<>();

    public GameTeam(Player player){
        playersOnTeam.add(player);
    }

    public boolean IsPlayer(Player player){ return playersOnTeam.contains(player); }
    public boolean CanPlayerJoin(int numberPerTeam){return playersOnTeam.size() < numberPerTeam;}
    public void AddPlayer(Player player){playersOnTeam.add(player);}
    public void SendMessage(String message){ for(var player : playersOnTeam) player.sendMessage(message); }
    public void Teleport(Location location){ for(var player : playersOnTeam) player.teleport(location); }

}
