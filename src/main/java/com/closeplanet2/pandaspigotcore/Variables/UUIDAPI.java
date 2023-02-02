package com.closeplanet2.pandaspigotcore.Variables;

import java.util.UUID;

public class UUIDAPI implements VariableLogic {
    @Override
    public boolean IsType(String variable) {
        try {
            var uuid = UUID.fromString(variable);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Object ReturnFrom(String variable) {
        return UUID.fromString(variable);
    }
}
