package com.arja.runeforge.util;

import com.arja.runeforge.block.ModBlocks;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammableBlocks
{
    public static void register()
    {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ASH_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ASH_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ASH_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ASH_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_ASH_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.ASH_LEAVES, 30, 60);
    }
}
