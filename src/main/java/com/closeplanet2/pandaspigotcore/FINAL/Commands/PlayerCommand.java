package com.closeplanet2.pandaspigotcore.FINAL.Commands;

import com.closeplanet2.pandaspigotcore.FINAL.Commands.Enums.ErrorType;
import com.closeplanet2.pandaspigotcore.FINAL.Commands.Interfaces.*;
import com.closeplanet2.pandaspigotcore.FINAL.Variables.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.*;

public abstract class PlayerCommand extends BukkitCommand {

    private static class CustomMethod {
        private final String commandSignature;
        private final Method method;
        private final String[] permission;
        private final String[] tabCompleteOn;
        private final String[] tabComplete;
        private final boolean op;

        public CustomMethod(Method method, String commandName){
            var sb = new StringBuilder(commandName);
            if(method.isAnnotationPresent(CommandSignature.class))  sb.append(".").append(method.getAnnotation(CommandSignature.class).value());
            permission = method.isAnnotationPresent(CommandPermission.class) ? method.getAnnotation(CommandPermission.class).value() : null;
            op = method.isAnnotationPresent(CommandOP.class) && method.getAnnotation(CommandOP.class).value();
            tabCompleteOn = method.isAnnotationPresent(TabCompleteOn.class) ? method.getAnnotation(TabCompleteOn.class).value() : null;
            tabComplete = method.isAnnotationPresent(TabComplete.class) ? method.getAnnotation(TabComplete.class).value() : null;
            for(var param : method.getParameterTypes()) sb.append(".").append(param.getSimpleName());
            commandSignature = sb.toString();
            this.method = method;
        }

        private boolean TestSignature(Player commandSender, String commandName, String[] playerInput, boolean testBefore){
            var sb = new StringBuilder(commandName);
            var commandSignature = method.isAnnotationPresent(CommandSignature.class) ? method.getAnnotation(CommandSignature.class) : null;
            if(commandSignature != null) sb.append(".").append(commandSignature.value());
            var doesCommandSignatureMatch = commandSignature == null || DoesCommandSignatureMatch(playerInput, commandSignature);
            if(!doesCommandSignatureMatch) return false;

            var serialisedPlayerInput = commandSignature == null ? playerInput : RemoveCommandSignatureFromMatch(playerInput, commandSignature);
            var testBefore_serialisedPlayerInput = TestForTestBefore(testBefore, serialisedPlayerInput);
            var classArguments = ConvertArgsToClass(commandSender, testBefore_serialisedPlayerInput);
            for(var param : classArguments) sb.append(".").append(param.getSimpleName());

            return this.commandSignature.equalsIgnoreCase(sb.toString());
        }

        private String[] TestForTestBefore(boolean testBefore, String[] serialisedPlayerInput){
            if(!testBefore) return serialisedPlayerInput;
            else return Arrays.copyOf(serialisedPlayerInput, serialisedPlayerInput.length-1);
        }

        private boolean DoesCommandSignatureMatch(String[] playerInput, CommandSignature commandSignature){
            if(commandSignature.value().equals("")) return true;
            var commandParts = commandSignature.value().split("\\.");
            for(var i = 0; i < commandParts.length; i++)
                if(i >= playerInput.length) return false;
                else if(!commandParts[i].equalsIgnoreCase(playerInput[i])) return false;
            return true;
        }

        private String[] RemoveCommandSignatureFromMatch(String[] playerInput, CommandSignature commandSignature){
            var commandParts = commandSignature != null ? commandSignature.value().split("\\.") : new String[]{};
            var returnData = new ArrayList<String>();
            for (var i = commandParts.length; i < playerInput.length; i++) returnData.add(playerInput[i]);
            return returnData.toArray(new String[0]);
        }

        private Class[] ConvertArgsToClass(Player commandSender, String[] playerInput){
            var args = new Object[playerInput.length + 1];
            args[0] = commandSender;
            for(var i = 0; i < playerInput.length; i++) args[i + 1] = playerInput[i];

            var convertArgs = new Class[args.length];
            convertArgs[0] = UUID.class;
            for(var i = 1; i < args.length; i++){
                var arg = args[i];
                if(new BooleanArgument().IsType((String) arg)) convertArgs[i] = boolean.class;
                else if(new IntegerArgument().IsType((String) arg)) convertArgs[i] = int.class;
                else if(new FloatArgument().IsType((String) arg)) convertArgs[i] = float.class;
                else if(new UUIDArgument().IsType((String) arg)) convertArgs[i] = UUID.class;
                else if(new CharacterArgument().IsType((String) arg)) convertArgs[i] = Character.class;
                else convertArgs[i] = String.class;
            }
            return convertArgs;
        }

        private ErrorType CanPlayerUseCommand(Player player){
            if(permission != null){
                for(var perm : permission) if(!player.hasPermission(perm)) return ErrorType.Command_With_Matching_Signature_Requires_Permission;
            }
            if(op && !player.isOp()) return ErrorType.Command_With_Matching_Signature_Requires_Op;
            return ErrorType.No_Error;
        }

        private void InvokeMethod(PlayerCommand playerCommand, Player player, String[] pi) {
            var commandSignature = method.isAnnotationPresent(CommandSignature.class) ? method.getAnnotation(CommandSignature.class) : null;
            var playerInput = RemoveCommandSignatureFromMatch(pi, commandSignature);

            var parameterTypes = method.getParameterTypes();
            var invokeArgs = new Object[playerInput.length + 1];
            invokeArgs[0] = player.getUniqueId();

            for (int i = 0; i < playerInput.length; i++) {
                if ((parameterTypes[i + 1] == Boolean.class || parameterTypes[i + 1] == boolean.class) && new BooleanArgument().IsType(playerInput[i])) {
                    invokeArgs[i + 1] = new BooleanArgument().ReturnFrom(playerInput[i]);
                } else if ((parameterTypes[i + 1] == Integer.class || parameterTypes[i + 1] == int.class) && new IntegerArgument().IsType(playerInput[i])) {
                    invokeArgs[i + 1] = new IntegerArgument().ReturnFrom(playerInput[i]);
                } else if ((parameterTypes[i + 1] == Float.class || parameterTypes[i + 1] == float.class) && new FloatArgument().IsType(playerInput[i])) {
                    invokeArgs[i + 1] = new FloatArgument().ReturnFrom(playerInput[i]);
                } else if (parameterTypes[i + 1] == UUID.class && new UUIDArgument().IsType(playerInput[i])) {
                    invokeArgs[i + 1] = new UUIDArgument().ReturnFrom(playerInput[i]);
                } else if ((parameterTypes[i + 1] == Character.class || parameterTypes[i + 1] == char.class) && new CharacterArgument().IsType(playerInput[i])) {
                    invokeArgs[i + 1] = new CharacterArgument().ReturnFrom(playerInput[i]);
                } else {
                    invokeArgs[i + 1] = playerInput[i];
                }
            }

            playerCommand.InvokeVoid(method, invokeArgs);
        }
    }

    private final List<CustomMethod> methodArray = new ArrayList<>();
    public PlayerCommand(String commandName){
        super(commandName.toLowerCase());
        for(var method : this.getClass().getMethods()){
            if(!method.isAnnotationPresent(CommandReturn.class)) continue;
            methodArray.add(new CustomMethod(method, commandName));
        }
        var names = new ArrayList<String>();
        names.add(commandName.toLowerCase());
        setAliases(names);
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] args){
        if(!(commandSender instanceof Player)) return false;
        var player = (Player) commandSender;
        return TestMethods(player, s, args);
    }

    private boolean TestMethods(Player commandSender, String s, String[] args) {
        var firedCommand = false;
        for(var customMethod : methodArray){
            if(customMethod.TestSignature(commandSender, s, args, false)){
                firedCommand = true;
                var errorType = customMethod.CanPlayerUseCommand(commandSender);
                if(errorType == ErrorType.No_Error){
                    customMethod.InvokeMethod(this, commandSender, args);
                    break;
                }else{
                    Error(errorType);
                }
            }
        }
        if(!firedCommand) Error(ErrorType.No_Matching_Command_Signature_In_Class);
        return false;
    }

    public abstract void InvokeVoid(Method method, Object[] invokeArgs);
    public abstract void Error(ErrorType errorType);

    @Override
    public List<String> tabComplete(CommandSender commandSender, String s, String[] args) throws IllegalArgumentException {
        if(!(commandSender instanceof Player)) new ArrayList<String>();
        var player = (Player) commandSender;

        var data = new ArrayList<String>();
        for(var customMethod : methodArray){
            if(customMethod.TestSignature(player, s, args, false)){
                var errorType = customMethod.CanPlayerUseCommand(player);
                if(errorType == ErrorType.No_Error){
                    if(customMethod.tabCompleteOn != null) Collections.addAll(data, customMethod.tabCompleteOn);
                }
            }
            else if(customMethod.TestSignature(player, s, args, true)){
                var errorType = customMethod.CanPlayerUseCommand(player);
                if(errorType == ErrorType.No_Error){
                    if(customMethod.tabComplete != null) Collections.addAll(data, customMethod.tabComplete);
                }
            }
        }
        return data;
    }
}
