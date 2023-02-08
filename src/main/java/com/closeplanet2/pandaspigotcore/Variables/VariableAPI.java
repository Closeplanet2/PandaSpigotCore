package com.closeplanet2.pandaspigotcore.Variables;

import java.util.Map;
import java.util.UUID;

public class VariableAPI {
    public static Map<Class<?>, VariableLogic> VariableTests(){
        return Map.ofEntries(
                Map.entry(Boolean.class, new BooleanArgument()),
                Map.entry(Character.class, new CharacterArgument()),
                Map.entry(Double.class, new DoubleArgument()),
                Map.entry(Float.class, new FloatArgument()),
                Map.entry(Integer.class, new IntegerArgument()),
                Map.entry(Long.class, new LongArgument()),
                Map.entry(UUID.class, new UUIDArgument())
        );
    }
}
