package com.arja.runeforge.rune;

import com.arja.runeforge.Runeforge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class RuneItemBase extends Item
{
    // in ticks
    protected int cooldownTime = 100;

    public RuneItemBase(Settings settings)
    {
        super(settings);
    }

    public void setCooldownTime(int cooldownTime)
    {
        this.cooldownTime = cooldownTime;
    }

    public int getCooldownTime()
    {
        return cooldownTime;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);

        if (user.getItemCooldownManager().isCoolingDown(itemStack))
        {
            return ActionResult.FAIL;
        }

        user.getItemCooldownManager().set(itemStack, cooldownTime);

        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        return super.useOnBlock(context);
    }

    // The translation must be exactly under "rune.description.rune-forge:{rune-id}
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        tooltip.add(Text.translatable("rune.description." + Registries.ITEM.getId(stack.getItem())));

        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null)
        {
            ItemCooldownManager cooldownManager = player.getItemCooldownManager();

            if (cooldownManager.isCoolingDown(stack))
            {
                float cooldownProgress = cooldownManager.getCooldownProgress(stack,
                        MinecraftClient.getInstance().getRenderTickCounter().getTickDelta(false));
                int remainingTicks = (int)(getCooldownTime() * (cooldownProgress * 100) / 100);

                if (Runeforge.ticksToSeconds(remainingTicks) < 60)
                {
                    tooltip.add(Text.translatable("tooltip.rune.cooldown.seconds", Runeforge.ticksToSeconds(remainingTicks))
                            .formatted(Formatting.AQUA));
                }
                else
                {
                    tooltip.add(Text.translatable("tooltip.rune.cooldown.minutes", Runeforge.ticksToMinutes(remainingTicks))
                            .formatted(Formatting.AQUA));
                }
            }
            else if (cooldownTime > 0)
            {
                int seconds = Runeforge.ticksToSeconds(getCooldownTime());

                if (seconds < 60)
                {
                    tooltip.add(Text.translatable("tooltip.rune.cooldown.seconds", seconds).formatted(Formatting.AQUA));
                }
                else
                {
                    tooltip.add(Text.translatable("tooltip.rune.cooldown.minutes", Runeforge.ticksToMinutes(getCooldownTime()))
                            .formatted(Formatting.AQUA));
                }
            }
        }

    }
}
