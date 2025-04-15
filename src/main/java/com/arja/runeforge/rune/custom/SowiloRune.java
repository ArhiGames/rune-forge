package com.arja.runeforge.rune.custom;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.rune.RuneItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SowiloRune extends RuneItemBase
{
    public SowiloRune(Settings settings)
    {
        super(settings);

        setCooldownTime(Runeforge.minutesToTicks(3));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        ActionResult superResult = super.use(world, user, hand);
        if (superResult != ActionResult.SUCCESS)
        {
            return superResult;
        }

        Vec3d pos = user.getPos();
        double radius = 10.0;

        List<Entity> nearbyEntities = world.getEntitiesByClass(
                Entity.class,
                new Box(pos.x - radius, pos.y - radius, pos.z - radius, pos.x + radius, pos.y + radius, pos.z + radius),
                entity -> entity != user
        );

        for (Entity entity : nearbyEntities)
        {
            entity.setGlowing(true);
        }

        return ActionResult.SUCCESS;
    }
}
