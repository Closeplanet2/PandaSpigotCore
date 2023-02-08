package com.closeplanet2.pandaspigotcore.Variables;

public class FloatAPI implements VariableLogic{
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
    public boolean IsType(Object variable) {
        return IsType((String) variable);
    }

    @Override
    public Object ReturnFrom(String variable) {
        return Float.parseFloat(variable);
    }
}
