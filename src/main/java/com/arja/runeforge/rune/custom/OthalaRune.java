package com.arja.runeforge.rune.custom;

import com.arja.runeforge.rune.RuneItemBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

public class OthalaRune extends RuneItemBase
{

    public OthalaRune(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        ActionResult superResult = super.use(world, user, hand);
        if (superResult != ActionResult.SUCCESS)
        {
            return superResult;
        }
        if (world.isClient) return ActionResult.SUCCESS;

        ItemStack stack = user.getStackInHand(hand);

        ServerPlayerEntity serverPlayerEntity = ((ServerPlayerEntity)user);

        BlockPos spawnPoint = serverPlayerEntity.getSpawnPointPosition();
        RegistryKey<World> spawnPointDimension = serverPlayerEntity.getSpawnPointDimension();
        if (spawnPoint == null || spawnPointDimension == null)
        {
            return ActionResult.FAIL;
        }
        MinecraftServer server = world.getServer();

        TeleportTarget target = new TeleportTarget(server.getWorld(spawnPointDimension),
                new Vec3d(spawnPoint.getX() + 0.5f, spawnPoint.getY() + 1.f, spawnPoint.getZ() + 0.5f),
                new Vec3d(0, 0, 0),
                user.getYaw(),
                user.getPitch(),
                TeleportTarget.NO_OP);
        user.teleportTo(target);

        stack.decrement(1);

        return ActionResult.CONSUME;
    }
}
