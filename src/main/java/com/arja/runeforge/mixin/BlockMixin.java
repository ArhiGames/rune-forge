package com.arja.runeforge.mixin;

import com.arja.runeforge.component.ModDataComponents;
import com.arja.runeforge.component.custom.RuneComponent;
import com.arja.runeforge.item.ModItems;
import com.arja.runeforge.rune.RuneManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.context.ContextParameterMap;
import net.minecraft.util.context.ContextType;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(Block.class)
public abstract class BlockMixin
{
    @Inject(
            method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void smeltDroppedStacks(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir)
    {
        if (entity instanceof PlayerEntity player)
        {
            if (!RuneManager.hasRune(new RuneComponent(Registries.ITEM.getId(ModItems.RUNE_KENAZ).toString()), player.getMainHandStack()))
            {
                return;
            }

            List<ItemStack> drops = new ArrayList<>();
            List<ItemStack> originalDrops = cir.getReturnValue();

            MinecraftServer server = world.getServer();
            Map<Identifier, Recipe<?>> smeltableRecipes = getSmeltableRecipes(server);
            for (ItemStack drop : originalDrops)
            {
                smeltableRecipes.forEach((id, recipe) ->
                {
                    if (recipe.getIngredientPlacement().getIngredients().getFirst().test(drop))
                    {
                        ItemStack itemStack = recipe.getDisplays().getFirst().result().getFirst(new ContextParameterMap.Builder().build(new ContextType.Builder().build()));
                        itemStack.setCount(drop.getCount());
                        drops.add(itemStack);
                    }
                });
            }

            if (!drops.isEmpty())
            {
                cir.setReturnValue(drops);
            }
        }
    }

    @Unique
    private static Map<Identifier, Recipe<?>> getSmeltableRecipes(MinecraftServer server)
    {
        return server.getRecipeManager().values().stream()
                .filter(recipeEntry -> recipeEntry.value().getType() == RecipeType.SMELTING)
                .collect(Collectors.toMap(
                        recipeEntry -> recipeEntry.id().getValue(),
                        RecipeEntry::value
                ));
    }
}
