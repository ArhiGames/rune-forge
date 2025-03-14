package com.arja.runeforge.datagen;

import com.arja.runeforge.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider
{
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate()
    {

        addDrop(ModBlocks.ASH_LOG);
        addDrop(ModBlocks.ASH_WOOD);
        addDrop(ModBlocks.STRIPPED_ASH_WOOD);
        addDrop(ModBlocks.STRIPPED_ASH_LOG);
        addDrop(ModBlocks.ASH_PLANKS);
        addDrop(ModBlocks.ASH_SAPLING);

        addDrop(ModBlocks.ASH_LEAVES, leavesDrops(ModBlocks.ASH_LEAVES, ModBlocks.ASH_SAPLING,0.065F));
    }
}
