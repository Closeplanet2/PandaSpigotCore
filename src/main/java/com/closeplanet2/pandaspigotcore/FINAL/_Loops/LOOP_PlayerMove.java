package com.closeplanet2.pandaspigotcore.FINAL._Loops;

import com.closeplanet2.pandaspigotcore.FINAL.Loop.LoopValues;
import com.closeplanet2.pandaspigotcore.FINAL.Loop.PandaLoop;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import com.closeplanet2.pandaspigotcore.FINAL.Player.Enums.MOVE_STATE;
import com.closeplanet2.pandaspigotcore.FINAL.Player.PlayerAPI;
import org.bukkit.Bukkit;

@PandaLoop
public class LOOP_PlayerMove implements LoopValues {
    @Override
    public String loopName() {
        return "LOOP_PlayerMove";
    }

    @Override
    public int initLoop() {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(PandaSpigotCore.INSTANCE, new Runnable() {
            public void run() {
                for(var player : Bukkit.getOnlinePlayers()){
                    if(PlayerAPI.RETURN_MOVE_STATE(player) == MOVE_STATE.LOCKED){
                        player.teleport(PandaSpigotCore.INSTANCE.STORE_PLAYER_LOCATION.get(player.getUniqueId()));
                    }
                }
            }
        }, 20, 20);
    }
}
