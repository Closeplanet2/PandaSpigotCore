package com.closeplanet2.pandaspigotcore.FINAL.Matchmaking.Interface;

import com.closeplanet2.pandaspigotcore.FINAL.ExternalAPIS.CitizensAPI;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface GameSettingsObject {
    World gameWorld(String newWorldName);
    Location lobbyLocation(World world);
    HashMap<String, Object> customData();
    List<Location> spawnPoints(World world);
    default List<CitizensAPI.NPCCreator> citizensNPCS(World world){ return new ArrayList<>(); }
}
