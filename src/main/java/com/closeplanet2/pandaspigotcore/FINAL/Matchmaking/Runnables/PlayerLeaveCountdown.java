package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Runnables;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.GameNetworkCallbacks;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.Game;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerLeaveCountdown extends BukkitRunnable {

    private final GameNetworkCallbacks gameNetworkCallbacks;
    private final Game game;
    private final Player player;
    private int time;

    public PlayerLeaveCountdown(GameNetworkCallbacks gameNetworkCallbacks, Game game, Player player, int time){
        this.time = time;
        this.game = game;
        this.player = player;
        this.gameNetworkCallbacks = gameNetworkCallbacks;
    }

    @Override
    public void run() {
        time -= 1;
        Bukkit.broadcastMessage(time + "");
        if(time == 0){
            game.RemovePlayerOnTimeExpire(gameNetworkCallbacks, player);
            cancel();
        }
    }

}
