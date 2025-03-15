package com.arja.runeforge;

import com.arja.runeforge.block.ModBlocks;
import com.arja.runeforge.command.ModCommands;
import com.arja.runeforge.component.ModDataComponents;
import com.arja.runeforge.item.ModItems;
import com.arja.runeforge.util.ModFlammableBlocks;
import com.arja.runeforge.util.ModStrippableBlocks;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Runeforge implements ModInitializer
{
	public static final String MOD_ID = "rune-forge";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		LOGGER.info("Initializing " + MOD_ID);

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModCommands.registerCommands();

		ModDataComponents.registerDataComponents();

		ModStrippableBlocks.register();
		ModFlammableBlocks.register();
	}
}