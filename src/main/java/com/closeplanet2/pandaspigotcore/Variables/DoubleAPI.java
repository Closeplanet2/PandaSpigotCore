package com.closeplanet2.pandaspigotcore.Variables;

public class DoubleAPI implements VariableLogic{
    @Override
    public boolean IsType(String variable) {
        try {
            double x = Double.parseDouble(variable);
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
        return Double.parseDouble(variable);
    }
}
