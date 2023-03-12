package com.closeplanet2.pandaspigotcore.FINAL._Events;

import com.closeplanet2.pandaspigotcore.FINAL.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@PandaEvent
public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        CheckIfLeavingGame(event.getPlayer());
    }

    private void CheckIfLeavingGame(Player player){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.IsPlayerInGame(player)){
                for(var networkCallback : PandaSpigotCore.INSTANCE.gameNetworkCallbacks){
                    networkCallback.PlayerLeaveRoom(game, player);
                }
            }
        }
    }

}
