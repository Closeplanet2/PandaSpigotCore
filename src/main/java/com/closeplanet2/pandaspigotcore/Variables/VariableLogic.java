package com.closeplanet2.pandaspigotcore.Variables;

public interface VariableLogic {
    boolean IsType(String variable);
    boolean IsType(Object variable);
    Object ReturnFrom(String variable);
}
