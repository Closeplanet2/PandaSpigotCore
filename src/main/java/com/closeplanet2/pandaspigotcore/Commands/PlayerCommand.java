package com.closeplanet2.pandaspigotcore.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

public abstract class PlayerCommand extends BukkitCommand {

    private String commandName;
    private boolean op;
    private String perm;
    private String pass;

    public PlayerCommand(String commandName, boolean op, String perm, String pass, String... names){
        super(commandName);
        this.commandName = commandName;
        this.op = op;
        this.perm = perm;
        this.pass = pass;
        this.setAliases(Arrays.asList(names));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {

        if(!(sender instanceof Player)){
            onFail(ChatColor.RED + "The sender is not a instance of player!");
            return false;
        }

        var player = (Player) sender;

        if(op && !player.isOp()){
            onFail(ChatColor.RED + "The sender is not OP!");
            return false;
        }

        if(perm != null){
            if(!player.hasPermission(perm)){
                onFail(ChatColor.RED + "The sender is not OP!");
                return false;
            }
        }

        if(pass != null){
            if(!Arrays.stream(args).toList().contains(pass)){
                onFail(ChatColor.RED + "The args don't contain the specified password!");
                return false;
            }
        }

        if(!CommandAPI.CanPlayerSendCommands(player)){
            onFail(ChatColor.RED + "Your commands have been frozen!");
            return false;
        }

        onSuccess(player, args);
        return true;
    }

    public abstract void onSuccess(Player player, String[] args);
    public abstract void onFail(String reason);

    public String commandName(){
        return commandName;
    }
}
