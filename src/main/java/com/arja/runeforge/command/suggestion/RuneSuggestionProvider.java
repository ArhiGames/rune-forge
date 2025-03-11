package com.arja.runeforge.command.suggestion;

import com.arja.runeforge.item.ModItems;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.ServerCommandSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public class RuneSuggestionProvider implements SuggestionProvider<ServerCommandSource>
{
    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> commandContext, SuggestionsBuilder suggestionsBuilder) throws CommandSyntaxException
    {
        Collection<String> runeNames = new ArrayList<>();
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_KENAZ).toString());

        for (String runeName : runeNames)
        {
            suggestionsBuilder.suggest(runeName);
        }

        return suggestionsBuilder.buildFuture();
    }
}
