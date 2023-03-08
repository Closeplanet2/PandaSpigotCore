package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.Game;
import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object.GameSettings;
import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;
import org.bukkit.entity.Player;

public class GameManager {
    public static void FindRandomRoom(GameNetworkCallbacks networkCallbacks, Player player){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.TestGameSettings(null, -1)){
                networkCallbacks.FindRandomRoomSuccess(game, player);
                return;
            }
        }
        networkCallbacks.FindRandomRoomFailed("Cannot Find Game!", player);
    }

    public static void FindRoom(GameNetworkCallbacks networkCallbacks, GameSettings gameSettings, Player player){
        for(var gameUUID : PandaSpigotCore.INSTANCE.networkGames.keySet()){
            var game = PandaSpigotCore.INSTANCE.networkGames.get(gameUUID);
            if(game.TestGameSettings(gameSettings.gameType(), gameSettings.perTeam())){ networkCallbacks.FindRoomSuccess(game, player); }
        }
        networkCallbacks.FindRoomFailed(gameSettings, "Cannot Find Game!", player);
    }

    public static void CreateRoom(GameNetworkCallbacks networkCallbacks, String gameName, GameSettings gameSettings, Player player){
        var game = new Game(gameName, gameSettings);
        if(PandaSpigotCore.INSTANCE.networkGames.containsKey(game.gameUUID)){
            networkCallbacks.CreateRoomFailed(gameSettings, "Game Found With ID!", player);
            return;
        }
        PandaSpigotCore.INSTANCE.networkGames.put(game.gameUUID, game);
        networkCallbacks.CreateRoomSuccess(game, player);
    }
}
