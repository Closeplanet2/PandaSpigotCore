package com.closeplanet2.pandaspigotcore.FINAL.Variables;

public class FloatArgument implements VariableLogic{
    @Override
    public boolean IsType(String variable) {
        try {
            float x = Float.parseFloat(variable);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public Object ReturnFrom(String variable) {
        return Float.parseFloat(variable);
    }
}
