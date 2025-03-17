package com.arja.runeforge.util;

import com.arja.runeforge.block.ModBlocks;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class ModStrippableBlocks
{
    public static void register()
    {
        StrippableBlockRegistry.register(ModBlocks.ASH_LOG, ModBlocks.STRIPPED_ASH_LOG);
        StrippableBlockRegistry.register(ModBlocks.ASH_WOOD, ModBlocks.STRIPPED_ASH_WOOD);
    }
}
