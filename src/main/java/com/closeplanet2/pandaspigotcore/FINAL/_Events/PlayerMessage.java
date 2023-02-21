package com.closeplanet2.pandaspigotcore.FINAL._Events;

import com.closeplanet2.pandaspigotcore.FINAL.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.FINAL.MessageSystem.MessageAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@PandaEvent
public class PlayerMessage implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent chatEvent){
        MessageAPI.HandleChatEvent(chatEvent);
    }

}
