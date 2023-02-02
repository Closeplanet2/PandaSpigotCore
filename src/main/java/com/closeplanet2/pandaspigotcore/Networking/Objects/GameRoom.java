package com.closeplanet2.pandaspigotcore.Networking.Objects;

import com.closeplanet2.pandaspigotcore.Networking.Enums.PlayerState;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GameRoom {
    private World gameWorld;
    private Location lobbyLocation;
    private List<Location> spawnPoints;
    private HashMap<GamePlayer, PlayerState> playerStates;
    private boolean isTeamGame;
}
