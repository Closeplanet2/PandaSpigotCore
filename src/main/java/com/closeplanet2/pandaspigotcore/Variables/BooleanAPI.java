package com.closeplanet2.pandaspigotcore.Variables;

public class BooleanAPI implements VariableLogic {
    @Override
    public boolean IsType(String variable) {
        return variable.equalsIgnoreCase("false") || variable.equalsIgnoreCase("true");
    }

    @Override
    public boolean IsType(Object variable) {
        return IsType((String) variable);
    }

    @Override
    public Object ReturnFrom(String variable) {
        return Boolean.parseBoolean(variable);
    }
}
