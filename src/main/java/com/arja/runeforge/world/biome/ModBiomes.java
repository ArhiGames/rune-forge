package com.arja.runeforge.world.biome;

import com.arja.runeforge.Runeforge;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes
{
    public static final RegistryKey<Biome> FROZEN_FOREST_KEY = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Runeforge.MOD_ID, "frozen_forest"));

    public static void bootstrap(Registerable<Biome> context)
    {
        context.register(FROZEN_FOREST_KEY, frozenForest(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder)
    {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome frozenForest(Registerable<Biome> context)
    {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRAY, 300, 4, 4));
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 300, 2, 3));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        DefaultBiomeFeatures.addAncientDebris(biomeBuilder);
        DefaultBiomeFeatures.addFossils(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultGrass(biomeBuilder);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(3.0f)
                .temperature(-2.0f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xAEEAF2)
                        .waterFogColor(0xAEEAF2)
                        .skyColor(0x708090)
                        .grassColor(0xA4B5AA)
                        .foliageColor(0xA4B5AA)
                        .fogColor(0x708090)
                        .moodSound(BiomeMoodSound.CAVE)
                        .build())
                .build();
    }
}
