package com.arja.runeforge.world.gen;

import com.arja.runeforge.world.ModPlacedFeatures;
import com.arja.runeforge.world.biome.ModBiomes;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModTreeGeneration
{
    public static void generateTrees()
    {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(ModBiomes.FROZEN_FOREST_KEY),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ASH_PLACED_KEY);
    }
}
