package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Object;

import com.closeplanet2.pandaspigotcore.FINAL.Console.ConsoleCore;

import java.util.Objects;

public record GameSettings(String gameType, int minPlayers, int maxPlayers, boolean isVisible, int perTeam) {
    public boolean TestGameSettings(String gameType, int currentPlayers, int perTeam){
        var isGameType = gameType == null || this.gameType.equals(gameType);
        var standard = isGameType && isVisible && currentPlayers < maxPlayers;
        return perTeam <= 0 ? standard : perTeam == this.perTeam && standard;
    }
}
