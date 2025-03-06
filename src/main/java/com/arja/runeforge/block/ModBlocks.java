package com.arja.runeforge.block;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks
{
    public static final Block LEMON_BLOCK = register(
            "lemon_block",
            Block::new,
            AbstractBlock.Settings.create().sounds(BlockSoundGroup.MUD),
            true);

    private static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem)
    {
        RegistryKey<Block> blockKey = keyOfBlock(name);
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        if (shouldRegisterItem)
        {
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name)
    {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Runeforge.MOD_ID, name));
    }

    private static RegistryKey<Item> keyOfItem(String name)
    {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Runeforge.MOD_ID, name));
    }

    public static void registerModBlocks()
    {
        Runeforge.LOGGER.info("Registering Mod Blocks for " + Runeforge.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ModItems.MAGIC_TOOLS_ITEM_GROUP_KEY).register((itemGroup) ->
        {
            itemGroup.add(LEMON_BLOCK.asItem());
        });
    }
}
