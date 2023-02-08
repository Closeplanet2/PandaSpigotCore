package com.closeplanet2.pandaspigotcore.Commands.Version2;

import com.closeplanet2.pandaspigotcore.Commands.Version2.Interfaces.CommandError;
import com.closeplanet2.pandaspigotcore.Commands.Version2.Interfaces.CommandReturn;
import com.closeplanet2.pandaspigotcore.Commands.Version2.Interfaces.PandaCommand;
import com.closeplanet2.pandaspigotcore.Console.ConsoleCore;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

@PandaCommand
public class TestCommand extends PlayerCommand{

    public TestCommand() { super("Test"); }

    @CommandReturn
    public void Test_Return(CommandSender player){
        ConsoleCore.Send("DEFAULT");
    }

    @CommandError
    public void Test_Error(){
        ConsoleCore.Send("Error");
    }

}
