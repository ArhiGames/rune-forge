package com.arja.runeforge.mixin;

import com.arja.runeforge.item.ModItems;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin
{
    @Inject(
            method = "onDeath",
            at = @At("HEAD")
    )
    public void onDeath(DamageSource damageSource, CallbackInfo ci)
    {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        for (int i = 0; i < player.getInventory().main.size(); i++)
        {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.getItem() == ModItems.RUNE_ANSUZ)
            {
            }
        }
    }

    @Inject(
            method = "copyFrom",
            at = @At("RETURN")
    )
    public void copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo ci)
    {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        player.experienceLevel = oldPlayer.experienceLevel;
        player.totalExperience = oldPlayer.totalExperience;
        player.experienceProgress = oldPlayer.experienceProgress;
        player.setScore(oldPlayer.getScore());
    }
}
