package com.closeplanet2.pandaspigotcore.Networking.Objects;

import org.bukkit.entity.Player;

public class GamePlayer {
    private Player player = null;
    private GameTeam gameTeam = null;

    public GamePlayer(Player player){
        this.player = player;
    }

    public GamePlayer(GameTeam gameTeam){
        this.gameTeam = gameTeam;
    }
}
