package com.closeplanet2.pandaspigotcore.FINAL.Variables;

public class IntegerArgument implements VariableLogic{
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
    public Object ReturnFrom(String variable) {
        return Integer.parseInt(variable);
    }
}
