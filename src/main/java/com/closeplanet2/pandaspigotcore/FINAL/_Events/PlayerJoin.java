package com.closeplanet2.pandaspigotcore.FINAL._Events;

import com.closeplanet2.pandaspigotcore.FINAL.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.FINAL.Player.Enums.HEALTH_STATE;
import com.closeplanet2.pandaspigotcore.FINAL.Player.PlayerAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@PandaEvent
public class PlayerJoin implements Listener {

    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event){
        var player = event.getPlayer();
        PlayerAPI.CHANGE_STATE(player, HEALTH_STATE.CANT_INCREASE);
    }

}
