package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Objects;

import com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Enums.SettingType;

import java.util.HashMap;

public class GameSettings {
    private String displayName;
    private String gameType;
    private int minPlayers;
    private int maxPlayers;
    private HashMap<SettingType, Boolean> settingMap = new HashMap<SettingType, Boolean>();

    public GameSettings(String displayName, String gameType, int minPlayers, int maxPlayers){
        this.displayName = displayName;
        this.gameType = gameType;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public void UpdateSettingMap(SettingType settingType){
        settingMap.put(settingType, !settingMap.get(settingType));
    }

    public void UpdateSettingMap(SettingType settingType, boolean value){
        settingMap.put(settingType, value);
    }
}
