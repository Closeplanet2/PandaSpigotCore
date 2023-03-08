package com.closeplanet2.pandaspigotcore.FINAL.Block;

import com.closeplanet2.pandaspigotcore.FINAL.Location.Objects.CustomLocation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.List;

public class BlockAPI {
    public static List<Block> FindBedsNearby(CustomLocation center, int radius){
        return FindBedsNearby(center.getLocation(), radius);
    }

    public static List<Block> FindBedsNearby(Location center, int radius){
        var beds = new ArrayList<Block>();

        for(var x = center.getBlockX() - radius; x <= center.getBlockX() + radius; x++){
            for(var y = center.getBlockY() - radius; y <= center.getBlockY() + radius; y++){
                for(var z = center.getBlockZ() - radius; z <= center.getBlockZ() + radius; z++){
                    if(center.getWorld() == null) continue;
                    var block = center.getWorld().getBlockAt(x, y, z);
                    if(!IsBedType(block)) continue;
                    beds.add(block);
                }
            }
        }

        return beds;
    }

    private static boolean IsBedType(Block block){
        return block.getType() == Material.RED_BED || block.getType() == Material.BLACK_BED ||
                block.getType() == Material.BLUE_BED || block.getType() == Material.BROWN_BED ||
                block.getType() == Material.CYAN_BED || block.getType() == Material.GRAY_BED ||
                block.getType() == Material.GREEN_BED || block.getType() == Material.LIGHT_BLUE_BED ||
                block.getType() == Material.LIGHT_GRAY_BED || block.getType() == Material.LIME_BED ||
                block.getType() == Material.MAGENTA_BED || block.getType() == Material.ORANGE_BED ||
                block.getType() == Material.PINK_BED || block.getType() == Material.PURPLE_BED ||
                block.getType() == Material.WHITE_BED || block.getType() == Material.YELLOW_BED;
    }
}
