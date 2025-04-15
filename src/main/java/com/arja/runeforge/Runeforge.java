package com.arja.runeforge;

import com.arja.runeforge.block.ModBlocks;
import com.arja.runeforge.command.ModCommands;
import com.arja.runeforge.component.ModDataComponents;
import com.arja.runeforge.item.ModItems;
import com.arja.runeforge.rune.RuneManager;
import com.arja.runeforge.util.ModFlammableBlocks;
import com.arja.runeforge.util.ModStrippableBlocks;
import com.arja.runeforge.world.gen.ModWorldGeneration;
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

		RuneManager.registerRuneManager();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModCommands.registerCommands();

		ModDataComponents.registerDataComponents();

		ModWorldGeneration.generateModWorldGen();

		ModStrippableBlocks.register();
		ModFlammableBlocks.register();
	}

	public static int secondsToTicks(int seconds)
	{
		return seconds * 20;
	}

	public static int minutesToTicks(int minutes)
	{
		return secondsToTicks(minutes * 60);
	}

	public static int ticksToSeconds(int ticks)
	{
		return ticks / 20;
	}

	public static int ticksToMinutes(int ticks)
	{
		if (ticks >= 1200)
		{
			return ticksToSeconds(ticks) / 60;
		}
		return 0;
	}
}