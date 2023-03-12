package com.closeplanet2.pandaspigotcore.FINAL._Events;

import com.closeplanet2.pandaspigotcore.FINAL.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

@PandaEvent
public class BlockRedstone implements Listener {

    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.IsLocationInWorld(event.getBlock().getLocation())){
                for(var callback : PandaSpigotCore.INSTANCE.gameNetworkCallbacks){
                    callback.BlockRedstoneEvent(event, game);
                }
            }
        }
    }

}
