package com.closeplanet2.pandaspigotcore.Commands.Version2;

import com.closeplanet2.pandaspigotcore.Commands.Version2.Interfaces.PandaCommand;
import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@PandaCommand
public class TestCommand extends PlayerCommand{

    public TestCommand() {
        super("TestCommand");
        names.add("TestCommand");
    }

    public void CustomCommand(CommandSender player){
        ConsoleCore.Send("Test1");
    }

    public void CustomCommand(CommandSender player, int arg1){
        ConsoleCore.Send("Test2");
    }

    public void CustomCommand(CommandSender player, int arg1, String arg2){
        ConsoleCore.Send("Test3");
    }

}
