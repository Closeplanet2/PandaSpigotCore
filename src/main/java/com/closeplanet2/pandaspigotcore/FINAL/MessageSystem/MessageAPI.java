package com.closeplanet2.pandaspigotcore.FINAL.MessageSystem;

import com.closeplanet2.pandaspigotcore.PandaSpigotCore;
import com.closeplanet2.pandaspigotcore.FINAL.Player.Enums.MESSAGE_STATE;
import com.closeplanet2.pandaspigotcore.FINAL.Player.PlayerAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageAPI {
    public static void HandleChatEvent(AsyncPlayerChatEvent event){
        var sender = event.getPlayer();
        var senderState = PlayerAPI.RETURN_MESSAGE_STATE(sender);
        if(senderState == MESSAGE_STATE.CAN_GET_CANT_SEND || senderState == MESSAGE_STATE.CANT_GET_CANT_SEND) return;

        for(var handler : event.getRecipients()){
            var handlerState = PlayerAPI.RETURN_MESSAGE_STATE(handler);
            if(handlerState == MESSAGE_STATE.CANT_GET_CAN_SEND || handlerState == MESSAGE_STATE.CANT_GET_CANT_SEND){
                event.getRecipients().remove(handler);
            }
        }
    }

    public static void ADD_STORED(String messageName, String message){
        PandaSpigotCore.INSTANCE.storedMessages.put(messageName, message);
    }

    public static String RETURN_STORED(String messageName){
        return PandaSpigotCore.INSTANCE.storedMessages.getOrDefault(messageName, null);
    }

    public static void SEND_STORED(Player player, String messageName){
        var message = RETURN_STORED(messageName);
        if(message == null) return;
        SEND_TO_PLAYER(player, message);
    }

    public static void SEND_TO_PLAYER(Player player, Object message){
        SEND_TO_PLAYER(player, message.toString());
    }

    public static void SEND_TO_PLAYER(Player player, String message){
        var message_state = PlayerAPI.RETURN_MESSAGE_STATE(player);
        if(message_state == MESSAGE_STATE.CANT_GET_CAN_SEND || message_state == MESSAGE_STATE.CANT_GET_CANT_SEND) return;
        player.sendMessage(MessageSerialiser.SerialiseMessage(message));
    }

    public static class MessageSerialiser {
        public static String SerialiseMessage(String message){
            return ChatColor.translateAlternateColorCodes('&', message);
        }
    }
}
