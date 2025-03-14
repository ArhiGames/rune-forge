package com.arja.runeforge.component;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.component.custom.RuneComponent;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class ModDataComponents
{
    public static final ComponentType<RuneComponent> RUNE_COMPONENT_TYPE = register("rune_component",
            runeComponentBuilder -> runeComponentBuilder.codec(RuneComponent.CODEC));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator)
    {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Runeforge.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponents()
    {
        Runeforge.LOGGER.info("Registering Data Components for " + Runeforge.MOD_ID);
    }
}
