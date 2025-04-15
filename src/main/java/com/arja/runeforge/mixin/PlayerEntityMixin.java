package com.arja.runeforge.mixin;

import com.arja.runeforge.item.ModItems;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
    @Inject(
            method = "applyDamage",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void applyDamage(ServerWorld world, DamageSource source, float amount, CallbackInfo ci)
    {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player.getOffHandStack().getItem() == ModItems.RUNE_ALGIZ)
        {
            amount = 0.0f;
            ci.cancel();
        }
    }
}
