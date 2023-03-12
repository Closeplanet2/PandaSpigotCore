package com.closeplanet2.pandaspigotcore.FINAL._Events;

import com.closeplanet2.pandaspigotcore.FINAL.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@PandaEvent
public class NPCClick implements Listener {
    @EventHandler
    public void onRightClick(NPCRightClickEvent event){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.IsLocationInWorld(event.getNPC().getStoredLocation())){
                for(var callback : PandaSpigotCore.INSTANCE.gameNetworkCallbacks){
                    callback.NPCRightClickEvent(event.getNPC().getEntity(), event.getClicker(), game);
                }
            }
        }
    }

    @EventHandler
    public void onLeftClick(NPCLeftClickEvent event){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.IsLocationInWorld(event.getNPC().getStoredLocation())){
                for(var callback : PandaSpigotCore.INSTANCE.gameNetworkCallbacks){
                    callback.NPCLeftClickEvent(event.getNPC().getEntity(), event.getClicker(), game);
                }
            }
        }
    }
}
