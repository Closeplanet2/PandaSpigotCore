package com.closeplanet2.pandaspigotcore.FINAL._Events;

import com.closeplanet2.pandaspigotcore.FINAL.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.FINAL.Player.Enums.HUNGER_STATE;
import com.closeplanet2.pandaspigotcore.FINAL.Player.PlayerAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

@PandaEvent
public class PlayerHunger implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onPlayerHunger(FoodLevelChangeEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        var player = (Player) event.getEntity();
        var hungerState = PlayerAPI.RETURN_HUNGER_STATE(player);

        var currentAmount = player.getFoodLevel();
        var nextAmount = event.getFoodLevel();

        if(hungerState == HUNGER_STATE.LOCKED){
            event.setCancelled(true);
        } else if(hungerState == HUNGER_STATE.CANT_DROP && nextAmount < currentAmount){
            event.setCancelled(true);
        }else if(hungerState == HUNGER_STATE.CANT_INCREASE && nextAmount > currentAmount){
            event.setCancelled(true);
        }
    }

}
