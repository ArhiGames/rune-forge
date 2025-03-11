package com.arja.runeforge;

import com.arja.runeforge.block.ModBlocks;
import com.arja.runeforge.command.ModCommands;
import com.arja.runeforge.command.suggestion.RuneSuggestionProvider;
import com.arja.runeforge.item.ModItems;
import com.arja.runeforge.world.gen.ModWorldGeneration;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runeforge implements ModInitializer
{
	public static final String MOD_ID = "rune-forge";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		LOGGER.info("Hello Fabric world!");

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModCommands.registerCommands();
	}

	public static void applyRune(ItemStack stack, String runeType)
	{
		//stack.set(DataComponentTypes.CUSTOM_NAME, new StringC);
	}
}