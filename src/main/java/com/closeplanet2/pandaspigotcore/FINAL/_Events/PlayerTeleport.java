package com.closeplanet2.pandaspigotcore.FINAL._Events;

import com.closeplanet2.pandaspigotcore.FINAL.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import com.closeplanet2.pandaspigotcore.FINAL.Player.Enums.TELEPORT_STATE;
import com.closeplanet2.pandaspigotcore.FINAL.Player.PlayerAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

@PandaEvent
public class PlayerTeleport implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onTeleport(PlayerTeleportEvent playerTeleportEvent){
        var player = playerTeleportEvent.getPlayer();
        if(!PandaSpigotCore.INSTANCE.playerTeleportStates.containsKey(player.getUniqueId()));
        var teleportState =  PlayerAPI.RETURN_TELEPORT_STATE(player);

        if(teleportState == TELEPORT_STATE.LOCKED){
            playerTeleportEvent.setCancelled(true);
        }
    }

}
