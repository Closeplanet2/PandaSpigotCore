package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Runnables;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.GameNetworkCallbacks;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.Game;
import com.closeplanet2.pandaspigotcore.FINAL.MessageSystem.MessageAPI;
import org.bukkit.scheduler.BukkitRunnable;

public class GameLobbyCountdown extends BukkitRunnable {

    private final GameNetworkCallbacks gameNetworkCallbacks;
    private int time;
    private final Game game;

    public GameLobbyCountdown(GameNetworkCallbacks gameNetworkCallbacks, Game game, int time){
        this.time = time;
        this.game = game;
        this.gameNetworkCallbacks = gameNetworkCallbacks;
    }

    @Override
    public void run() {
        time -= 1;
        if(!gameNetworkCallbacks.GameLobbyCountdown(game, time)){ cancel(); }
    }
}
