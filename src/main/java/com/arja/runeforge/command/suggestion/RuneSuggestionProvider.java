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
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_GEBO).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_JERA).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_HAGALAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_FEHU).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_URUZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_THURISAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_ANSUZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_RAIDHO).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_WUNJO).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_NAUTHIZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_ISA).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_EIHWAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_PERTHRO).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_ALGIZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_SOWILO).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_TIWAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_BERKANO).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_EHWAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_MANNAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_LAGUZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_INGWAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_DAGAZ).toString());
        runeNames.add(Registries.ITEM.getId(ModItems.RUNE_OTHALA).toString());

        for (String runeName : runeNames)
        {
            suggestionsBuilder.suggest(runeName);
        }

        return suggestionsBuilder.buildFuture();
    }
}
