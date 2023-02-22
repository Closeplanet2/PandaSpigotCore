package com.closeplanet2.pandaspigotcore.FINAL.StoredData;

import com.closeplanet2.pandaspigotcore.FINAL.PandaSpigotCore;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataAPI {

    public static void AddKeyData(UUID uuid, Object key, Object data){ AddKeyData(uuid, key.toString(), data); }
    public static void AddKeyData(UUID uuid, String key, Object data){
        if(!PandaSpigotCore.INSTANCE.playerDataRegister.containsKey(uuid)) PandaSpigotCore.INSTANCE.playerDataRegister.put(uuid, new HashMap<>());
        PandaSpigotCore.INSTANCE.playerDataRegister.get(uuid).put(key, data);
    }

    public static Object ReturnKeyData(UUID uuid, Object key){ return ReturnKeyData(uuid, key.toString()); }
    public static Object ReturnKeyData(UUID uuid, String key){
        if(!PandaSpigotCore.INSTANCE.playerDataRegister.containsKey(uuid)) PandaSpigotCore.INSTANCE.playerDataRegister.put(uuid, new HashMap<>());
        return PandaSpigotCore.INSTANCE.playerDataRegister.get(uuid).get(key);
    }

    public static void RemoveKeyData(UUID uuid, Object key){ RemoveKeyData(uuid, key.toString()); }
    public static void RemoveKeyData(UUID uuid, String key){
        if(!PandaSpigotCore.INSTANCE.playerDataRegister.containsKey(uuid)) PandaSpigotCore.INSTANCE.playerDataRegister.put(uuid, new HashMap<>());
        PandaSpigotCore.INSTANCE.playerDataRegister.get(uuid).remove(key);
    }

}
