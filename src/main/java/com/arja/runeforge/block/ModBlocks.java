package com.arja.runeforge.block;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.item.ModItemGroups;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
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

    public static final Block GINUNGAGAP = register(
            "ginungagab",
            Block::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BLACK)
                    .pistonBehavior(PistonBehavior.BLOCK)
                    .dropsNothing()
                    .luminance(state -> 8),
            true
    );

    //------------------------------------------------------------------------------------------------------------------
    //Ash tree
    //------------------------------------------------------------------------------------------------------------------
    public static final Block ASH_LOG = register("ash_log", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_LOG), true);
    public static final Block ASH_WOOD = register("ash_wood", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_WOOD), true);
    public static final Block STRIPPED_ASH_LOG = register("stripped_ash_log", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG), true);
    public static final Block STRIPPED_ASH_WOOD = register("stripped_ash_wood", PillarBlock::new, AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD), true);
    public static final Block ASH_PLANKS = register("ash_planks", Block::new, AbstractBlock.Settings.copy(Blocks.OAK_PLANKS), true);
    public static final Block ASH_LEAVES = register("ash_leaves", Block::new, AbstractBlock.Settings.copy(Blocks.OAK_LEAVES), true);
    //public static final Block ASH_SAPLING = register("ash_sapling", SaplingBlock::new, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING), true);

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

        Registry.register(Registries.ITEM_GROUP, ModItemGroups.MAGIC_BLOCKS_ITEM_GROUP_KEY, ModItemGroups.MAGIC_BLOCKS_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MAGIC_BLOCKS_ITEM_GROUP_KEY).register((itemGroup) ->
        {
            itemGroup.add(GINUNGAGAP.asItem());
            itemGroup.add(ASH_LEAVES.asItem());
            //itemGroup.add(ASH_SAPLING.asItem());
            itemGroup.add(ASH_LOG.asItem());
            itemGroup.add(STRIPPED_ASH_LOG.asItem());
            itemGroup.add(ASH_WOOD.asItem());
            itemGroup.add(STRIPPED_ASH_WOOD.asItem());
            itemGroup.add(ASH_PLANKS.asItem());
        });
    }
}
