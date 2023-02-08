package com.closeplanet2.pandaspigotcore.Player;

import com.closeplanet2.pandaspigotcore.PandaSpigotCore;
import com.closeplanet2.pandaspigotcore.Player.Enums.*;
import com.closeplanet2.pandaspigotcore.Variables.UUIDArgument;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerAPI {
    public static OfflinePlayer SearchForOfflinePlayer(String uuidOrName){
        if(new UUIDArgument().IsType(uuidOrName)) return Bukkit.getOfflinePlayer(UUID.fromString(uuidOrName));
        return null;
    }

    public static Player SearchForPlayer(String uuidOrName){
        if(new UUIDArgument().IsType(uuidOrName)) return Bukkit.getPlayer(UUID.fromString(uuidOrName));
        return Bukkit.getPlayer(uuidOrName);
    }

    public static COMMAND_STATE RETURN_COMMAND_STATE(Player player){
        return PandaSpigotCore.INSTANCE.playerCommandStates.getOrDefault(
                player.getUniqueId(), COMMAND_STATE.NORMAL
        );
    }

    public static EXP_STATE RETURN_EXP_STATE(Player player){
        return PandaSpigotCore.INSTANCE.playerEXPStates.getOrDefault(
                player.getUniqueId(), EXP_STATE.NORMAL
        );
    }
    public static HEALTH_STATE RETURN_HEALTH_STATE(Player player){
        return PandaSpigotCore.INSTANCE.playerHealthStates.getOrDefault(
                player.getUniqueId(), HEALTH_STATE.NORMAL
        );
    }
    public static HUNGER_STATE RETURN_HUNGER_STATE(Player player){
        return PandaSpigotCore.INSTANCE.playerHungerStates.getOrDefault(
                player.getUniqueId(), HUNGER_STATE.NORMAL
        );
    }
    public static MESSAGE_STATE RETURN_MESSAGE_STATE(Player player){
        return PandaSpigotCore.INSTANCE.playerMessageStates.getOrDefault(
                player.getUniqueId(), MESSAGE_STATE.CAN_GET_CAN_SEND
        );
    }
    public static MOVE_STATE RETURN_MOVE_STATE(Player player){
        return PandaSpigotCore.INSTANCE.playerMoveStates.getOrDefault(
                player.getUniqueId(), MOVE_STATE.NORMAL
        );
    }
    public static TELEPORT_STATE RETURN_TELEPORT_STATE(Player player){
        return PandaSpigotCore.INSTANCE.playerTeleportStates.getOrDefault(
                player.getUniqueId(), TELEPORT_STATE.NORMAL
        );
    }

    public static void CHANGE_STATE(Player player, COMMAND_STATE command_state){
        PandaSpigotCore.INSTANCE.playerCommandStates.put(player.getUniqueId(), command_state);
    }
    public static void CHANGE_STATE(Player player, EXP_STATE exp_state){
        PandaSpigotCore.INSTANCE.playerEXPStates.put(player.getUniqueId(), exp_state);
    }
    public static void CHANGE_STATE(Player player, HEALTH_STATE health_state){
        PandaSpigotCore.INSTANCE.playerHealthStates.put(player.getUniqueId(), health_state);
    }
    public static void CHANGE_STATE(Player player, HUNGER_STATE hunger_state){
        PandaSpigotCore.INSTANCE.playerHungerStates.put(player.getUniqueId(), hunger_state);
    }
    public static void CHANGE_STATE(Player player, MESSAGE_STATE message_state){
        PandaSpigotCore.INSTANCE.playerMessageStates.put(player.getUniqueId(), message_state);
    }
    public static void CHANGE_STATE(Player player, MOVE_STATE move_state){
        PandaSpigotCore.INSTANCE.playerMoveStates.put(player.getUniqueId(), move_state);
    }
    public static void CHANGE_STATE(Player player, TELEPORT_STATE teleport_state){
        PandaSpigotCore.INSTANCE.STORE_PLAYER_LOCATION.put(player.getUniqueId(), player.getLocation());
        PandaSpigotCore.INSTANCE.playerTeleportStates.put(player.getUniqueId(), teleport_state);
    }
}
