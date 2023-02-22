package com.closeplanet2.pandaspigotcore.FINAL.FileSystem;

import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomClass;
import com.closeplanet2.pandaspigotcore.FINAL.FileSystem.Interfaces.CustomConfig;

import java.util.HashMap;
import java.util.UUID;

public class TestSaveConfig implements CustomConfig {
    @Override
    public String ReturnConfigID() {
        return "TestSaveConfig";
    }

    @Override
    public Class<?> ReturnClassType() {
        return TestSaveConfig.class;
    }

    public HashMap<Integer, String> data1 = new HashMap<>();
    public HashMap<UUID, String> data = new HashMap<>();
    public HashMap<UUID, UUID> data3 = new HashMap<>();
}
