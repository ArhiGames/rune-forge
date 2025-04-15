package com.arja.runeforge.mixin;

import com.arja.runeforge.item.ModItems;
import com.arja.runeforge.rune.RuneManager;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin
{
    private boolean hadRuneOnDeath = false;
    private Map<ItemStack, Integer> algizProtectedItems = new HashMap<>();

    @Inject(
            method = "onDeath",
            at = @At("HEAD")
    )
    public void onDeath(DamageSource damageSource, CallbackInfo ci)
    {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.size(); i++)
        {
            ItemStack stack = inventory.getStack(i);
            if (stack.getItem() == ModItems.RUNE_ANSUZ)
            {
                this.hadRuneOnDeath = true;
                inventory.removeStack(i);
            }
            else if (RuneManager.hasRune(ModItems.RUNE_ALGIZ, stack))
            {
                algizProtectedItems.put(stack, i);
                inventory.removeStack(i);
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
        ServerPlayerEntityMixin oldPlayerMixin = (ServerPlayerEntityMixin)(Object)oldPlayer;
        assert oldPlayerMixin != null;

        if (oldPlayerMixin.hadRuneOnDeath)
        {
            player.experienceLevel = oldPlayer.experienceLevel;
            player.totalExperience = oldPlayer.totalExperience;
            player.experienceProgress = oldPlayer.experienceProgress;
        }
        for (Map.Entry<ItemStack, Integer> algizProtectedStack : oldPlayerMixin.algizProtectedItems.entrySet())
        {
            player.getInventory().setStack(algizProtectedStack.getValue(), algizProtectedStack.getKey());
        }
        algizProtectedItems.clear();

        player.setScore(oldPlayer.getScore());
    }
}
