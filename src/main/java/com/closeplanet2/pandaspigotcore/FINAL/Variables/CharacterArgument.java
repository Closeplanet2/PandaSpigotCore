package com.closeplanet2.pandaspigotcore.FINAL.Variables;

public class CharacterArgument implements VariableLogic {
    @Override
    public boolean IsType(String variable) {
        return variable.length() == 1;
    }

    @Override
    public Object ReturnFrom(String variable) {
        return variable.charAt(0);
    }
}
