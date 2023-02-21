package com.closeplanet2.pandaspigotcore.FINAL.Commands;

import com.closeplanet2.pandaspigotcore.FINAL.Commands.Interfaces.*;
import org.bukkit.Bukkit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

@PandaCommand
public class TestCommand extends PlayerCommand{

    public TestCommand() { super("TestCommand"); }

    @Override
    public void InvokeVoid(Method method, Object[] invokeArgs) {
        try { method.invoke(this, invokeArgs); } catch (IllegalAccessException | InvocationTargetException e) { e.printStackTrace(); }
    }

    @CommandReturn()
    @CommandPermission("Permission.Permission")
    public void Command_1(UUID uuid){
        var player = Bukkit.getPlayer(uuid);
        assert player != null;
        player.sendMessage("Hello");
    }

    @CommandReturn()
    public void Command_2(UUID uuid, int id){
        var player = Bukkit.getPlayer(uuid);
        assert player != null;
        player.sendMessage("Hello: " + id);
    }

    @CommandReturn()
    public void Command_2(UUID uuid, float id){
        var player = Bukkit.getPlayer(uuid);
        assert player != null;
        player.sendMessage("Hello f: " + id);
    }

}
