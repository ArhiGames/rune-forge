package com.arja.runeforge.rune;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RuneItemBase extends Item
{
    public RuneItemBase(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        return super.useOnBlock(context);
    }
}
