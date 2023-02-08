package com.closeplanet2.pandaspigotcore.Commands.Version2;

import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;
import com.closeplanet2.pandaspigotcore.Player.Enums.COMMAND_STATE;
import com.closeplanet2.pandaspigotcore.Player.PlayerAPI;
import com.google.common.reflect.Reflection;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public boolean execute(CommandSender commandSender, String s, String[] input) {
//        if(!(commandSender instanceof Player)) return false;
//        var player = (Player) commandSender;
//        if(op && !player.isOp()) return false;
//        if(!perm.equals("") && !player.hasPermission(perm)) return false;
//        if(!pass.equals("") && Arrays.stream(args).toList().contains(pass)) return false;
//        var commandState = PlayerAPI.RETURN_COMMAND_STATE(player);
//        if(commandState == COMMAND_STATE.LOCKED) return false;

//        var invokeArgs = new Object[args.length + 1];
//        invokeArgs[0] = player;
//        for(var i = 0; i < args.length; i++) invokeArgs[i + 1] = args[i];
//
//        var parameterTypes = new Class[args.length + 1];
//        parameterTypes[0] = Player.class;
//        for(var i = 0; i < args.length; i++) parameterTypes[i + 1] = String.class;
//
//        try {
//            var method = this.getClass().getMethod("CustomCommand", parameterTypes);
//            method.invoke(this, invokeArgs);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }

        var methodName = "CustomCommand";
        var args = new Object[input.length + 1];
        args[0] = commandSender;
        for(var i = 0; i < input.length; i++) args[i + 1] = input[i];

        for(var method : this.getClass().getMethods()){
            if(!method.getName().equals(methodName)) continue;


            var parameterTypes = method.getParameterTypes();
            if(parameterTypes.length != args.length) continue;

            var invokeArgs = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                var parameterType = parameterTypes[i];

            }
        }

        return true;
    }

}
