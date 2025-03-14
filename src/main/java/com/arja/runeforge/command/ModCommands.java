package com.arja.runeforge.command;

import com.arja.runeforge.command.suggestion.RuneSuggestionProvider;
import com.arja.runeforge.component.ModDataComponents;
import com.arja.runeforge.component.custom.RuneComponent;
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
                stack.set(ModDataComponents.RUNE_COMPONENT_TYPE, new RuneComponent(runeName));
            }
            else
            {
                player.sendMessage(Text.of("Couldn't apply rune called " + runeName + " to your main hand item!"), false);
            }
        }
        return 1;
    }
}
