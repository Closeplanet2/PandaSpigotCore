package com.closeplanet2.pandaspigotcore.Commands.Version2;

import com.closeplanet2.pandaspigotcore.Commands.Version2.Interfaces.CommandError;
import com.closeplanet2.pandaspigotcore.Commands.Version2.Interfaces.CommandReturn;
import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.Variables.*;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class PlayerCommand extends BukkitCommand {

    public String commandName;
    public boolean op = false;
    public String perm = "";
    public String pass = "";
    public List<String> names = new ArrayList<>();

    public PlayerCommand(String commandName){
        super(commandName);
        if(names.isEmpty()) names.add(commandName);
        this.setAliases(names);
        this.commandName = commandName;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] input){
        try {
            return Custom(commandSender, s, input);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            return false;
        }
    }

    private boolean Custom(CommandSender commandSender, String s, String[] input) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        var args = new Object[input.length + 1];
        args[0] = commandSender;
        Method commandError = null;
        for(var i = 0; i < input.length; i++) args[i + 1] = input[i];

        for(var method : this.getClass().getMethods()) {
            if(method.isAnnotationPresent(CommandError.class) && commandError == null) commandError = method;
            if(!method.isAnnotationPresent(CommandReturn.class)) continue;

            var parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != args.length) continue;

            var invokeArgs = new Object[args.length];
            var match = true;

            for (int i = 1; i < args.length; i++) {
                if ((parameterTypes[i] == Boolean.class || parameterTypes[i] == boolean.class) && new BooleanArgument().IsType((String) args[i])) {
                    invokeArgs[i] = new BooleanArgument().ReturnFrom((String) args[i]);
                } else if ((parameterTypes[i] == Character.class || parameterTypes[i] == char.class) && new CharacterArgument().IsType((String) args[i])) {
                    invokeArgs[i] = new CharacterArgument().ReturnFrom((String) args[i]);
                } else if ((parameterTypes[i] == Double.class || parameterTypes[i] == double.class) && new DoubleArgument().IsType((String) args[i])) {
                    invokeArgs[i] = new DoubleArgument().ReturnFrom((String) args[i]);
                } else if ((parameterTypes[i] == Integer.class || parameterTypes[i] == int.class) && new IntegerArgument().IsType((String) args[i])) {
                    invokeArgs[i] = new IntegerArgument().ReturnFrom((String) args[i]);
                } else if ((parameterTypes[i] == Long.class || parameterTypes[i] == long.class) && new LongArgument().IsType((String) args[i])) {
                    invokeArgs[i] = new LongArgument().ReturnFrom((String) args[i]);
                } else if ((parameterTypes[i] == Float.class || parameterTypes[i] == float.class) && new FloatArgument().IsType((String) args[i])) {
                    invokeArgs[i] = new FloatArgument().ReturnFrom((String) args[i]);
                } else if (parameterTypes[i] == UUID.class && new UUIDArgument().IsType((String) args[i])) {
                    invokeArgs[i] = new UUIDArgument().ReturnFrom((String) args[i]);
                } else {
                    match = false;
                    break;
                }
            }

            if (match) {
                method.invoke(this, invokeArgs);
                return true;
            }
        }

        if(commandError != null){
            commandError.invoke(new Object[]{});
        }

        return false;
    }

}
