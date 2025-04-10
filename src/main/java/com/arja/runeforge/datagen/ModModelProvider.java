package com.arja.runeforge.datagen;

import com.arja.runeforge.block.ModBlocks;
import com.arja.runeforge.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TexturedModel;

public class ModModelProvider extends FabricModelProvider
{
    public ModModelProvider(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LEMON_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GINUNGAGAP);

        blockStateModelGenerator.registerLog(ModBlocks.ASH_LOG).log(ModBlocks.ASH_LOG).wood(ModBlocks.ASH_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_ASH_LOG).log(ModBlocks.STRIPPED_ASH_LOG).wood(ModBlocks.STRIPPED_ASH_WOOD);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ASH_PLANKS);
        blockStateModelGenerator.registerSingleton(ModBlocks.ASH_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerTintableCross(ModBlocks.ASH_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(ModItems.KEBAB, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_KENAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_GEBO, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_JERA, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_HAGALAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_FEHU, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_URUZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_THURISAZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_ANSUZ, Models.GENERATED);
        itemModelGenerator.register(ModItems.RUNE_RAIDHO, Models.GENERATED);

        //itemModelGenerator.register(ModBlocks.ASH_SAPLING.asItem(), Models.GENERATED);
    }
}
