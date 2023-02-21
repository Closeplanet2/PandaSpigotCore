package com.closeplanet2.pandaspigotcore.FINAL.StoredData;

import com.closeplanet2.pandaspigotcore.PandaSpigotCore;

public class InternalDataAPI {

    public static void AddKeyData(Object key, Object data){ AddKeyData(key.toString(), data); }
    public static void AddKeyData(String key, Object data){ PandaSpigotCore.INSTANCE.internalDataRegister.put(key, data); }
    public static Object ReturnKeyData(Object key){ return ReturnKeyData(key.toString()); }
    public static Object ReturnKeyData(String key){ return PandaSpigotCore.INSTANCE.internalDataRegister.getOrDefault(key, null); }
    public static void RemoveKey(Object key){ RemoveKey(key.toString()); }
    public static void RemoveKey(String key){ PandaSpigotCore.INSTANCE.internalDataRegister.remove(key); }

}

