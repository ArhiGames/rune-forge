package com.arja.runeforge.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents
{
    public static final ConsumableComponent KEBAB_CONSUMABLE_COMPONENT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200, 1), 0.6f))
            .build();
    public static final FoodComponent KEBAB_FOOD_COMPONENT = new FoodComponent.Builder().nutrition(14).saturationModifier(0.8f).alwaysEdible().build();
}
