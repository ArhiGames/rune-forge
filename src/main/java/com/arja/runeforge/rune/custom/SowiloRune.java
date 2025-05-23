package com.arja.runeforge.rune.custom;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.rune.RuneItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class SowiloRune extends RuneItemBase
{
    private final int sowiloRemoveGlowCooldown = 60;
    private int passiveDamageTick = 0;

    public SowiloRune(Settings settings)
    {
        super(settings);

        setCooldownTime(Runeforge.minutesToTicks(3));
    }

    @Override
    public void onTickReceived(ServerPlayerEntity playerEntity)
    {
        passiveDamageTick++;
        if (passiveDamageTick % 20 == 0)
        {
            passiveDamageTick = 0;
            Vec3d pos = playerEntity.getPos();
            double radius = 10.0;

            List<Entity> nearbyEntities = playerEntity.getWorld().getEntitiesByClass(
                    Entity.class,
                    new Box(pos.x - radius, pos.y - radius, pos.z - radius, pos.x + radius, pos.y + radius, pos.z + radius),
                    entity -> entity != playerEntity
            );

            for (Entity entity : nearbyEntities)
            {
                if (entity instanceof LivingEntity livingEntity)
                {
                    if (livingEntity.hasStatusEffect(StatusEffects.GLOWING))
                    {
                        livingEntity.damage((ServerWorld)playerEntity.getWorld(), playerEntity.getDamageSources().magic(), 2.0f);
                    }
                }
            }
        }
    }

    private List<Entity> getNearbyEntities(World world, Vec3d userPosition, double radius, Entity ignoredEntity)
    {
        return world.getEntitiesByClass(
                Entity.class,
                new Box(userPosition.x - radius, userPosition.y - radius, userPosition.z - radius, userPosition.x + radius, userPosition.y + radius, userPosition.z + radius),
                entity -> entity != ignoredEntity
        );
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        if (world.isClient) return ActionResult.SUCCESS;

        List<Entity> nearbyEntities = getNearbyEntities(world, user.getPos(), 10, user);
        if (user.isSneaking())
        {
            if (!commitRuneCooldown(user, hand, sowiloRemoveGlowCooldown)) return ActionResult.FAIL;
            for (Entity entity : nearbyEntities)
            {
                if (entity instanceof LivingEntity)
                {
                    ((LivingEntity) entity).removeStatusEffect(StatusEffects.GLOWING);
                }
            }
            user.removeStatusEffect(StatusEffects.GLOWING);
        }
        else
        {
            ActionResult superResult = super.use(world, user, hand);
            if (superResult != ActionResult.SUCCESS)
            {
                return superResult;
            }
            for (Entity entity : nearbyEntities)
            {
                if (entity instanceof LivingEntity)
                {
                    ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,
                            Runeforge.minutesToTicks(5)));
                }
            }
        }

        return ActionResult.SUCCESS;
    }
}
