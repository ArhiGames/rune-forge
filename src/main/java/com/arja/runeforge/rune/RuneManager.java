package com.arja.runeforge.rune;

import com.arja.runeforge.component.ModDataComponents;
import com.arja.runeforge.component.custom.RuneComponent;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class RuneManager
{
    public static void registerRuneManager()
    {
        ItemTooltipCallback.EVENT.register((stack, context, type, lines) ->
        {
            if (hasAnyRune(stack))
            {
                lines.add(1, getTooltipForItem(stack).getFirst().copy().formatted(Formatting.LIGHT_PURPLE));
            }
        });
        ServerTickEvents.END_SERVER_TICK.register(server ->
        {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList())
            {
                for (ItemStack item : player.getInventory().main)
                {
                    if (item.getItem() instanceof RuneItemBase runeItem)
                    {
                        runeItem.onTickReceived(player);
                    }
                }
            }
        });
    }

    /**
     * Tries to apply a specified rune to the given item
     * @param runeComponent the component which should be applied
     * @param stack the item stack the component should be applied to
     * @return returns if the rune was applied successfully
     */
    public static boolean applyRune(RuneComponent runeComponent, ItemStack stack)
    {
        if (stack.isEmpty() || stack.contains(ModDataComponents.RUNE_COMPONENT_TYPE))
        {
            return false;
        }
        stack.set(ModDataComponents.RUNE_COMPONENT_TYPE, runeComponent);
        return true;
    }

    /**
     * Checks if the given item has a specified rune
     * @param runeComponent the rune component which should be checked
     * @param stack the stack where the rune component is searched
     * @return returns true if the rune has been found on the stack
     */
    public static boolean hasRune(RuneComponent runeComponent, ItemStack stack)
    {
        if (stack.contains(ModDataComponents.RUNE_COMPONENT_TYPE))
        {
            RuneComponent rune = stack.get(ModDataComponents.RUNE_COMPONENT_TYPE);
            if (rune != null)
            {
                return rune.runeId().equals(runeComponent.runeId());
            }
        }
        return false;
    }
    public static boolean hasRune(Item runeItem, ItemStack stack)
    {
        if (stack.contains(ModDataComponents.RUNE_COMPONENT_TYPE))
        {
            RuneComponent rune = stack.get(ModDataComponents.RUNE_COMPONENT_TYPE);
            if (rune != null)
            {
                return rune.runeId().equals(Registries.ITEM.getId(runeItem).toString());
            }
        }
        return false;
    }

    /**
     * Removes the given rune from an item stack
     * @param runeComponent the rune component which should be removed
     * @param stack the stack where the rune should be removed
     * @return returns true if the rune was removed successfully
     */
    public static boolean removeRune(RuneComponent runeComponent, ItemStack stack)
    {
        if (stack.contains(ModDataComponents.RUNE_COMPONENT_TYPE))
        {
            stack.remove(ModDataComponents.RUNE_COMPONENT_TYPE);
            return true;
        }
        return false;
    }

    /**
     * Returns if there is any rune on the given item
     * @param stack the item stack to check for
     * @return if there is any rune on stack
     */
    public static boolean hasAnyRune(ItemStack stack)
    {
        return stack.contains(ModDataComponents.RUNE_COMPONENT_TYPE);
    }

    /**
     * Gets the translated tooltip for the rune on the given item stack
     * @param stack the item stack to get the tool tip from
     * @return the list of tooltip lines
     */
    public static List<Text> getTooltipForItem(ItemStack stack)
    {
        List<Text> tooltip = new ArrayList<>();
        if (hasAnyRune(stack))
        {
            RuneComponent rune = stack.get(ModDataComponents.RUNE_COMPONENT_TYPE);
            if (rune != null)
            {
                tooltip.add(Text.translatable("rune.enchantment.type." + rune.runeId()));
            }
        }
        return tooltip;
    }
}
