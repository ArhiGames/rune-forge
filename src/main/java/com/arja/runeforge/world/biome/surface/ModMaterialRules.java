package com.arja.runeforge.world.biome.surface;

import com.arja.runeforge.block.ModBlocks;
import com.arja.runeforge.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

public class ModMaterialRules
{
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIAMOND_BLOCK);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GOLD_BLOCK);
    private static final MaterialRules.MaterialRule LEMON = makeStateRule(ModBlocks.LEMON_BLOCK);

    public static MaterialRules.MaterialRule makeRules()
    {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return MaterialRules.sequence(
                MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(ModBiomes.TEST_BIOME),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, GRASS_BLOCK)),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, LEMON)),
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block)
    {
        return MaterialRules.block(block.getDefaultState());
    }
}
