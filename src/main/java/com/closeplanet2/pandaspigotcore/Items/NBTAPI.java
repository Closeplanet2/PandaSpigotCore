package com.closeplanet2.pandaspigotcore.Items;

import com.closeplanet2.pandaspigotcore.PandaSpigotCore;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class NBTAPI {
    public static String GetNBT(ItemStack itemStack, String key){
        if(!itemStack.hasItemMeta()) return null;
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PandaSpigotCore.INSTANCE, key);
        if(pdc.has(namespacedKey, PersistentDataType.STRING)){
            return pdc.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    public static String GetNBT(Entity entity, String key){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PandaSpigotCore.INSTANCE,key);
        if(pdc.has(namespacedKey, PersistentDataType.STRING)) {
            return pdc.get(namespacedKey, PersistentDataType.STRING);
        }
        return null;
    }

    public static void AddNBT(ItemStack itemStack, String key, String value){
        ItemMeta meta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType());
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PandaSpigotCore.INSTANCE, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
        itemStack.setItemMeta(meta);
    }

    public static void AddNBT(Entity entity, String key, String value){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        NamespacedKey namespacedKey = new NamespacedKey(PandaSpigotCore.INSTANCE, key);
        pdc.set(namespacedKey, PersistentDataType.STRING, value);
    }

    public static boolean HasNBT(ItemStack itemStack, String key){
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(PandaSpigotCore.INSTANCE,key),PersistentDataType.STRING);
    }

    public static boolean HasNBT(Entity entity, String key){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.has(new NamespacedKey(PandaSpigotCore.INSTANCE,key),PersistentDataType.STRING);
    }

    public static void RemoveNBT(ItemStack itemStack, String key){
        if(!itemStack.hasItemMeta()) return;
        ItemMeta meta = itemStack.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(new NamespacedKey(PandaSpigotCore.INSTANCE,key));
        itemStack.setItemMeta(meta);
    }

    public static void RemoveNBT(Entity entity, String key){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        pdc.remove(new NamespacedKey(PandaSpigotCore.INSTANCE,key));
    }
}
