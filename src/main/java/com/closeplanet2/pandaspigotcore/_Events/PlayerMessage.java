package com.closeplanet2.pandaspigotcore._Events;

import com.closeplanet2.pandaspigotcore.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.MessageSystem.MessageAPI;
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
