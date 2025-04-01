package com.arja.runeforge.command;

import com.arja.runeforge.command.suggestion.RuneSuggestionProvider;
import com.arja.runeforge.component.custom.RuneComponent;
import com.arja.runeforge.rune.RuneManager;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ModCommands
{
    public static void registerCommands()
    {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("applyrune").then(
                    CommandManager.argument("rune_name", IdentifierArgumentType.identifier())
                            .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(1))
                            .suggests(new RuneSuggestionProvider())
                            .executes(ModCommands::executeApplyRuneCommand)
            ));
        });
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("removerune").then(
                    CommandManager.argument("rune_name", IdentifierArgumentType.identifier())
                            .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(1))
                            .suggests(new RuneSuggestionProvider())
                            .executes(ModCommands::executeRemoveRuneCommand)
            ));
        });
    }

    private static int executeApplyRuneCommand(CommandContext<ServerCommandSource> context)
    {
        ServerCommandSource source = context.getSource();
        String runeName = IdentifierArgumentType.getIdentifier(context, "rune_name").toString();
        if (source.getEntity() instanceof net.minecraft.server.network.ServerPlayerEntity player)
        {
            ItemStack stack = player.getMainHandStack();
            if (!RuneManager.applyRune(new RuneComponent(runeName), stack))
            {
                player.sendMessage(Text.of("Couldn't apply rune called " + runeName + " to your main hand item!"), false);
            }
        }
        return 1;
    }

    private static int executeRemoveRuneCommand(CommandContext<ServerCommandSource> context)
    {
        ServerCommandSource source = context.getSource();
        String runeName = IdentifierArgumentType.getIdentifier(context, "rune_name").toString();
        if (source.getEntity() instanceof net.minecraft.server.network.ServerPlayerEntity player)
        {
            ItemStack stack = player.getMainHandStack();
            if (!RuneManager.hasRune(new RuneComponent(runeName), stack))
            {
                player.sendMessage(Text.of("Couldn't remove rune called " + runeName + " from your main hand item!"), false);
            }
            else
            {
                RuneManager.removeRune(new RuneComponent(runeName), stack);
            }
        }
        return 1;
    }
}
