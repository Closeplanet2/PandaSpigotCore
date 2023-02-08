package com.closeplanet2.pandaspigotcore.Variables;

public class DoubleArgument implements VariableLogic{
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
    public Object ReturnFrom(String variable) {
        return Double.parseDouble(variable);
    }
}
