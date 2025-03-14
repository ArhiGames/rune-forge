package com.arja.runeforge.component.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record RuneComponent(String runeId)
{
    public static final Codec<RuneComponent> CODEC = RecordCodecBuilder.create(builder ->
    {
        return builder.group(
                Codec.STRING.fieldOf("runeId").forGetter(RuneComponent::runeId)
        ).apply(builder, RuneComponent::new);
    });
}
