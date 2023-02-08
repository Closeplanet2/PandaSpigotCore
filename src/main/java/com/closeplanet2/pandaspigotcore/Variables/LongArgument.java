package com.closeplanet2.pandaspigotcore.Variables;

public class LongArgument implements VariableLogic{
    @Override
    public boolean IsType(String variable) {
        try {
            long x = Long.parseLong(variable);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public Object ReturnFrom(String variable) {
        return Long.parseLong(variable);
    }
}
