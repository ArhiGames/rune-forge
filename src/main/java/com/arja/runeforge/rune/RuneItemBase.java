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
import net.minecraft.server.network.ServerPlayerEntity;
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

    public void onTickReceived(ServerPlayerEntity playerEntity)
    {

    }

    protected boolean commitRuneCooldown(PlayerEntity user, Hand hand, int cooldownTime)
    {
        ItemStack itemStack = user.getStackInHand(hand);
        
        if (user.getItemCooldownManager().isCoolingDown(itemStack))
        {
            return false;
        }

        user.getItemCooldownManager().set(itemStack, cooldownTime == -1 ? this.cooldownTime : cooldownTime);
        return true;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand)
    {
        if (!commitRuneCooldown(user, hand, -1))
        {
            return ActionResult.FAIL;
        }    

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
        String RuneTranslationKey = "rune.description." + Registries.ITEM.getId(stack.getItem());
        String FullText = Text.translatable(RuneTranslationKey).getString();

        String[] Lines = FullText.split("\n");

        for (String line : Lines)
        {
            tooltip.add(Text.literal(line));
        }

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
