package com.closeplanet2.pandaspigotcore._Events;

import com.closeplanet2.pandaspigotcore.Events.PandaEvent;
import com.closeplanet2.pandaspigotcore.Player.Enums.EXP_STATE;
import com.closeplanet2.pandaspigotcore.Player.PlayerAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

@PandaEvent
public class PlayerEXP implements Listener {

    @EventHandler(priority= EventPriority.HIGHEST)
    public void onEXPChange(PlayerExpChangeEvent event){
        var player = event.getPlayer();
        var expState = PlayerAPI.RETURN_EXP_STATE(player);

        var currentAmount = player.getTotalExperience();
        var nextAmount = currentAmount + event.getAmount();

        if(expState == EXP_STATE.LOCKED){
            event.setAmount(0);
        } else if(expState == EXP_STATE.CANT_DROP && nextAmount < currentAmount){
            event.setAmount(0);
        } else if(expState == EXP_STATE.CANT_INCREASE && nextAmount > currentAmount){
            event.setAmount(0);
        }
    }

}
