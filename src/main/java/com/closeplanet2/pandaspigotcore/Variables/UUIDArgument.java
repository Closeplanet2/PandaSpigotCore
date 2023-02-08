package com.closeplanet2.pandaspigotcore.Variables;

import java.util.UUID;

public class UUIDArgument implements VariableLogic {
    public static boolean IsTypeStatic(String variable){
        try {
            var uuid = UUID.fromString(variable);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean IsType(String variable) {
        return IsTypeStatic(variable);
    }

    @Override
    public Object ReturnFrom(String variable) {
        return UUID.fromString(variable);
    }
}
