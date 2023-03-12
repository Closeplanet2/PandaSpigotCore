package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.Game;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.GameSettings;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.UUID;

public interface GameNetworkCallbacks  {
    default void FindRandomRoomSuccess(Game game, Player player){ }
    default void FindRandomRoomFailed(String reason, Player player){ }
    default void FindRoomSuccess(Game game, Player player){}
    default void FindRoomFailed(GameSettings gameSettings, String reason, Player player){}
    default void CreateRoomSuccess(Game game, Player player){}
    default void CreateRoomFailed(GameSettings gameSettings, String reason, Player player){}
    default void PlayerLeaveRoom(Game game, Player player){}
    default boolean GameLobbyCountdown(Game game, int timeLeft){return true;}
    default void NoPlayerLeftInTeam(Game game, int indexOfTeam){}
    default void BlockRedstoneEvent(BlockRedstoneEvent event, Game game){}
    default void NPCRightClickEvent(Entity npcuuid, Player player, Game game){}
    default void NPCLeftClickEvent(Entity npcuuid, Player player, Game game){}
}
