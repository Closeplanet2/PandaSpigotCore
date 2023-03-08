package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.Game;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.GameSettings;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import org.bukkit.entity.Player;

public class GameManager {
    public static void FindRandomRoom(GameNetworkCallbacks networkCallbacks){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.TestGameSettings(null, -1)){
                networkCallbacks.FindRandomRoomSuccess(game);
                return;
            }
        }
        networkCallbacks.FindRandomRoomFailed();
    }

    public static void FindRoom(GameNetworkCallbacks networkCallbacks, GameSettings gameSettings){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.TestGameSettings(gameSettings.gameType(), gameSettings.perTeam())){ networkCallbacks.FindRoomSuccess(game); }
        }
        networkCallbacks.FindRoomFailed();
    }

    public static void CreateRoom(GameNetworkCallbacks networkCallbacks, String gameName, GameSettings gameSettings){
        var game = new Game(gameName, gameSettings);
        if(PandaSpigotCore.INSTANCE.networkGames.containsKey(game.gameUUID)){
            networkCallbacks.CreateRoomFailed();
            return;
        }
        PandaSpigotCore.INSTANCE.networkGames.put(game.gameUUID, game);
        networkCallbacks.CreateRoomSuccess(game);
    }
}
