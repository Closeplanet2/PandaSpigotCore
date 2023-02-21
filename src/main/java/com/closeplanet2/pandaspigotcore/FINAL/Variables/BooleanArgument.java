package com.closeplanet2.pandaspigotcore.FINAL.Variables;

public class BooleanArgument implements VariableLogic {
    @Override
    public boolean IsType(String variable) {
        return variable.equalsIgnoreCase("false") || variable.equalsIgnoreCase("true");
    }

    @Override
    public Object ReturnFrom(String variable) {
        return Boolean.parseBoolean(variable);
    }
}
