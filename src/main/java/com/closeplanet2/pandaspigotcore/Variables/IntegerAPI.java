package com.closeplanet2.pandaspigotcore.Variables;

public class IntegerAPI implements VariableLogic{
    @Override
    public boolean IsType(String variable) {
        try {
            int x = Integer.parseInt(variable);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public boolean IsType(Object variable) {
        return IsType((String) variable);
    }

    @Override
    public Object ReturnFrom(String variable) {
        return Integer.parseInt(variable);
    }
}
