package com.closeplanet2.pandaspigotcore._Events;

import com.closeplanet2.pandaspigotcore.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.Player.Enums.HEALTH_STATE;
import com.closeplanet2.pandaspigotcore.Player.PlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

@PandaEvent
public class PlayerHealth implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        var player  = (Player) event.getEntity();
        var healthState = PlayerAPI.RETURN_HEALTH_STATE(player);

        if(healthState == HEALTH_STATE.LOCKED){
            event.setCancelled(true);
        }else if(healthState == HEALTH_STATE.CANT_DROP && event.getDamage() > 0){
            event.setCancelled(true);
        }
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onEntityRegainHealth(EntityRegainHealthEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        var player  = (Player) event.getEntity();
        var healthState = PlayerAPI.RETURN_HEALTH_STATE(player);

        if(healthState == HEALTH_STATE.LOCKED){
            event.setCancelled(true);
        }else if(healthState == HEALTH_STATE.CANT_INCREASE && event.getAmount() > 0){
            event.setCancelled(true);
        }
    }

}