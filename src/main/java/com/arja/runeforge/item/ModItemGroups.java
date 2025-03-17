package com.arja.runeforge.item;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups
{
    public static final RegistryKey<ItemGroup> MAGIC_TOOLS_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(Runeforge.MOD_ID, "magic_tools"));
    public static final ItemGroup MAGIC_TOOLS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.KEBAB))
            .displayName(Text.translatable("itemgroup.magic_tools"))
            .build();

    public static final RegistryKey<ItemGroup> MAGIC_BLOCKS_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(Runeforge.MOD_ID, "magic_blocks"));
    public static final ItemGroup MAGIC_BLOCKS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.GINUNGAGAP.asItem()))
            .displayName(Text.translatable("itemgroup.magic_blocks"))
            .build();

    public static final RegistryKey<ItemGroup> RUNE_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(Runeforge.MOD_ID, "runes"));
    public static final ItemGroup RUNES_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.RUNE_KENAZ))
            .displayName(Text.translatable("itemgroup.runes"))
            .build();
}
