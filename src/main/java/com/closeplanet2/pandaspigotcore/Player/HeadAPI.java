package com.closeplanet2.pandaspigotcore.Player;

import com.closeplanet2.pandaspigotcore.Variables.UUIDAPI;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Base64;
import java.util.UUID;
import com.mojang.authlib.GameProfile;

public class HeadAPI {
    public static ItemStack ReturnPlayerHead(OfflinePlayer player){
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(player);
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public static ItemStack ReturnPlayerHead(String uuid){
        if(!UUIDAPI.IsTypeStatic(uuid)) return null;
        var offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
        return ReturnPlayerHead(offlinePlayer);
    }

    public static ItemStack ReturnPlayerHead(String name, int amount, String url) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        assert skullMeta != null;
        if (url.length() < 16) {
            skullMeta.setOwner(url);
            skullMeta.setDisplayName(name);
            skull.setItemMeta(skullMeta);
            return skull;
        }

        var s_url = new StringBuilder();
        s_url.append("https://textures.minecraft.net/texture/").append(url);
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
        byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", s_url.toString()).getBytes());
        gameProfile.getProperties().put("textures", new Property("textures", new String(data)));

        try {
            var field = skullMeta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(skullMeta, gameProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        skullMeta.setDisplayName(name);
        skull.setItemMeta(skullMeta);

        return skull;
    }
}
