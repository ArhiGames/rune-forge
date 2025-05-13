package com.arja.runeforge.rune.custom;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.rune.RuneItemBase;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;

public class NauthizRune extends RuneItemBase
{
    public NauthizRune(Settings settings)
    {
        super(settings);
    }

    @Override
    public void onTickReceived(ServerPlayerEntity playerEntity)
    {
        if (playerEntity.getHealth() <= 4.f)
        {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, Runeforge.secondsToTicks(10)));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, Runeforge.secondsToTicks(5)));
        }
    }
}
