package com.arja.runeforge.datagen;

import com.arja.runeforge.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.ASH_LOG.asItem())
                .add(ModBlocks.STRIPPED_ASH_LOG.asItem())
                .add(ModBlocks.ASH_WOOD.asItem())
                .add(ModBlocks.STRIPPED_ASH_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(ModBlocks.ASH_LOG.asItem())
                .add(ModBlocks.STRIPPED_ASH_LOG.asItem())
                .add(ModBlocks.ASH_WOOD.asItem())
                .add(ModBlocks.STRIPPED_ASH_WOOD.asItem());


        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.ASH_PLANKS.asItem());
    }
}
