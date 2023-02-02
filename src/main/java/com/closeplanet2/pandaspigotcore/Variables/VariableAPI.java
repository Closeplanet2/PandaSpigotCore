package com.closeplanet2.pandaspigotcore.Variables;

import java.util.Map;
import java.util.UUID;

public class VariableAPI {
    public static Map<Class<?>, VariableLogic> VariableTests(){
        return Map.ofEntries(
                Map.entry(Boolean.class, new BooleanAPI()),
                Map.entry(Character.class, new CharacterAPI()),
                Map.entry(Double.class, new DoubleAPI()),
                Map.entry(Float.class, new FloatAPI()),
                Map.entry(Integer.class, new IntegerAPI()),
                Map.entry(Long.class, new LongAPI()),
                Map.entry(UUID.class, new UUIDAPI())
        );
    }
}
