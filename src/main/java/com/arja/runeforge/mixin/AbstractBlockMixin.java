package com.arja.runeforge.mixin;

import com.arja.runeforge.component.custom.RuneComponent;
import com.arja.runeforge.item.ModItems;
import com.arja.runeforge.rune.RuneManager;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin
{
    @Inject(
            method = "onStacksDropped",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onBroken(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience, CallbackInfo ci)
    {
        if (RuneManager.hasRune(new RuneComponent(Registries.ITEM.getId(ModItems.RUNE_ANSUZ).toString()), tool))
        {
            ExperienceOrbEntity xpOrb = new ExperienceOrbEntity((World)world, pos.getX(), pos.getY(), pos.getZ(), 10);
            world.spawnEntity(xpOrb);
        }
    }
}
