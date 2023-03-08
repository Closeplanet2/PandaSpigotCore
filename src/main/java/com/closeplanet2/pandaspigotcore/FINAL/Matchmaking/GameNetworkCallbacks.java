package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.Game;

public interface GameNetworkCallbacks  {
    default void FindRandomRoomSuccess(Game game){ }
    default void FindRandomRoomFailed(){ }
    default void FindRoomSuccess(Game game){}
    default void FindRoomFailed(){}
    default void CreateRoomSuccess(Game game){}
    default void CreateRoomFailed(){}
}
