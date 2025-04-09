package com.arja.runeforge.world.tree;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.world.ModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class ModSaplingGenerators
{
    public static final SaplingGenerator ASH = new SaplingGenerator(Runeforge.MOD_ID + ":ash",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ASH_KEY), Optional.empty());
}
