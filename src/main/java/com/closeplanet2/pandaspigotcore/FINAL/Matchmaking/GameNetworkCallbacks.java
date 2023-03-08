package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.Game;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.GameSettings;
import org.bukkit.entity.Player;

public interface GameNetworkCallbacks  {
    default void FindRandomRoomSuccess(Game game, Player player){ }
    default void FindRandomRoomFailed(String reason, Player player){ }
    default void FindRoomSuccess(Game game, Player player){}
    default void FindRoomFailed(GameSettings gameSettings, String reason, Player player){}
    default void CreateRoomSuccess(Game game, Player player){}
    default void CreateRoomFailed(GameSettings gameSettings, String reason, Player player){}
}
