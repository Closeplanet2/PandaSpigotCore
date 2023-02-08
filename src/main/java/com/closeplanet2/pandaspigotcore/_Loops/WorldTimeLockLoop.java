package com.closeplanet2.pandaspigotcore._Loops;

import com.closeplanet2.pandaspigotcore.Loop.LoopValues;
import com.closeplanet2.pandaspigotcore.Loop.PandaLoop;
import com.closeplanet2.pandaspigotcore.PandaSpigotCore;
import com.closeplanet2.pandaspigotcore.Player.Enums.MOVE_STATE;
import com.closeplanet2.pandaspigotcore.Player.PlayerAPI;
import org.bukkit.Bukkit;

@PandaLoop
public class WorldTimeLockLoop implements LoopValues {

    @Override
    public String loopName() {
        return "WorldTimeLockLoop";
    }

    @Override
    public int initLoop() {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(PandaSpigotCore.INSTANCE, new Runnable() {
            public void run() {
                for(var uuid : PandaSpigotCore.INSTANCE.timeLockWorlds.keySet()){
                    var world = Bukkit.getWorld(uuid);
                    var timeOfDay = PandaSpigotCore.INSTANCE.timeLockWorlds.get(uuid);
                    if(world == null) continue;

                    switch (timeOfDay){
                        case DAY -> world.setTime(1000);
                        case NOON -> world.setTime(6000);
                        case SUNSET -> world.setTime(12000);
                        case NIGHT -> world.setTime(13000);
                        case MIDNIGHT -> world.setTime(18000);
                        case SUNRISE -> world.setTime(23000);
                    }
                }
            }
        }, 20, 20);
    }
}
