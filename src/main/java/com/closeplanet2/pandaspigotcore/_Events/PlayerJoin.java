package com.closeplanet2.pandaspigotcore._Events;

import com.closeplanet2.pandaspigotcore.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.PandaSpigotCore;
import com.closeplanet2.pandaspigotcore.Player.Enums.COMMAND_STATE;
import com.closeplanet2.pandaspigotcore.Player.Enums.EXP_STATE;
import com.closeplanet2.pandaspigotcore.Player.Enums.HEALTH_STATE;
import com.closeplanet2.pandaspigotcore.Player.Enums.HUNGER_STATE;
import com.closeplanet2.pandaspigotcore.Player.PlayerAPI;
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
