package com.arja.runeforge.rune;

import com.arja.runeforge.component.ModDataComponents;
import com.arja.runeforge.component.custom.RuneComponent;
import net.minecraft.item.ItemStack;

public class RuneManager
{
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
}
