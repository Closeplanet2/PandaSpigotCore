package com.closeplanet2.pandaspigotcore.Variables;

public class CharacterAPI implements VariableLogic {
    @Override
    public boolean IsType(String variable) {
        return variable.length() == 1;
    }

    @Override
    public boolean IsType(Object variable) {
        return IsType((String) variable);
    }

    @Override
    public Object ReturnFrom(String variable) {
        return variable.charAt(0);
    }
}
