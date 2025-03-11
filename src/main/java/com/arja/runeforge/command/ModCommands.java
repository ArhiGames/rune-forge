package com.arja.runeforge.command;

import com.arja.runeforge.command.suggestion.RuneSuggestionProvider;
import com.mojang.brigadier.arguments.StringArgumentType;
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
    }

    private static int executeApplyRuneCommand(CommandContext<ServerCommandSource> context)
    {
        ServerCommandSource source = context.getSource();
        String runeName = IdentifierArgumentType.getIdentifier(context, "rune_name").toString();
        if (source.getEntity() instanceof net.minecraft.server.network.ServerPlayerEntity player)
        {
            ItemStack stack = player.getMainHandStack();
            if (!stack.isEmpty())
            {
                player.sendMessage(Text.of("Die Rune '" + runeName + "' wurde angewendet!"), false);
            }
            else
            {
                player.sendMessage(Text.of("Halte ein Werkzeug in der Hand, um eine Rune anzuwenden!"), false);
            }
        }
        return 1;
    }
}
