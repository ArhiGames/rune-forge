package com.arja.runeforge.rune.custom;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.rune.RuneItemBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BerkanoRune extends RuneItemBase
{
    public BerkanoRune(Settings settings)
    {
        super(settings);

        setCooldownTime(Runeforge.minutesToTicks(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        if (world.isClient) return ActionResult.SUCCESS;
        ActionResult superResult = super.use(world, user, hand);
        if (superResult != ActionResult.SUCCESS)
        {
            return superResult;
        }
        BlockPos center = BlockPos.ofFloored(user.getPos());

        int radius = 5;
        BlockPos.iterate(center.add(-radius, -1, -radius), center.add(radius, 1, radius))
                .forEach(pos -> {
                    BlockState state = world.getBlockState(pos);
                    if (state.getBlock() instanceof CropBlock crop) {
                        int maxAge = crop.getMaxAge();
                        world.setBlockState(pos, crop.withAge(maxAge), 2);
                    }
                });
        return ActionResult.SUCCESS;
    }
}
