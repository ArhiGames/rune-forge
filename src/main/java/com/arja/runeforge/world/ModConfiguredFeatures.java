package com.arja.runeforge.world;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class ModConfiguredFeatures
{
    public static RegistryKey<ConfiguredFeature<?, ?>> ASH_KEY = registerKey("ash");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context)
    {
        register(context, ASH_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.ASH_LOG),
                new StraightTrunkPlacer(4, 5, 4),
                BlockStateProvider.of(ModBlocks.ASH_LEAVES),
                new CherryFoliagePlacer(
                        ConstantIntProvider.create(4), // Blätterradius (breit)
                        ConstantIntProvider.create(0), // Versatz (keine Lücken)
                        ConstantIntProvider.create(6), // Höhere Blätter, damit sie tiefer beginnen
                        0.25f, // Verzweigungsdichte (Anzahl der Zweige)
                        0.5f,  // Höhenvariation
                        0.4f,   // Verbreitung der Blätter
                        0.2f
                ),
                new TwoLayersFeatureSize(1, 0, 3) // Stellt sicher, dass die Krone mehr in der Mitte des Baumes ist
        ).ignoreVines().build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Runeforge.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
