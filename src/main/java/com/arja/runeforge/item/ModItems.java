package com.arja.runeforge.item;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.rune.RuneItemBase;
import com.arja.runeforge.rune.custom.SowiloRune;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;

public class ModItems
{
    public static final Item KEBAB = registerItem("kebab", Item::new, new Item.Settings()
            .food(ModFoodComponents.KEBAB_FOOD_COMPONENT, ModFoodComponents.KEBAB_CONSUMABLE_COMPONENT));

    public static final Item RUNE_KENAZ = registerItem("kenaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_GEBO = registerItem("gebo", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_JERA = registerItem("jera", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_HAGALAZ = registerItem("hagalaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_FEHU = registerItem("fehu", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_URUZ = registerItem("uruz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_THURISAZ = registerItem("thurisaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_ANSUZ = registerItem("ansuz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.EPIC).maxCount(1));
    public static final Item RUNE_RAIDHO = registerItem("raidho", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_WUNJO = registerItem("wunjo", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_NAUTHIZ = registerItem("nauthiz", RuneItemBase::new, new Item.Settings()
        .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_ISA = registerItem("isa", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_EIHWAZ = registerItem("eihwaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_PERTHRO = registerItem("perthro", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_ALGIZ = registerItem("algiz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.EPIC).maxCount(1));
    public static final Item RUNE_SOWILO = registerItem("sowilo", SowiloRune::new, new Item.Settings()
            .rarity(Rarity.COMMON).maxCount(1));
    public static final Item RUNE_TIWAZ = registerItem("tiwaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_BERKANO = registerItem("berkano", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_EHWAZ = registerItem("ehwaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_MANNAZ = registerItem("mannaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_LAGUZ = registerItem("laguz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_INGWAZ = registerItem("ingwaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_DAGAZ = registerItem("dagaz", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));
    public static final Item RUNE_OTHALA = registerItem("othala", RuneItemBase::new, new Item.Settings()
            .rarity(Rarity.RARE).maxCount(1));

    private static Item registerItem(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings)
    {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Runeforge.MOD_ID, name));

        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    public static void registerModItems()
    {
        Runeforge.LOGGER.info("Registering Mod Items for " + Runeforge.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemgroup) ->
                {
                    itemgroup.add(KEBAB);
                });

        Registry.register(Registries.ITEM_GROUP, ModItemGroups.MAGIC_TOOLS_ITEM_GROUP_KEY, ModItemGroups.MAGIC_TOOLS_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.MAGIC_TOOLS_ITEM_GROUP_KEY).register(itemgroup ->
        {
            itemgroup.add(KEBAB);
        });

        Registry.register(Registries.ITEM_GROUP, ModItemGroups.RUNE_ITEM_GROUP_KEY, ModItemGroups.RUNES_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.RUNE_ITEM_GROUP_KEY).register(itemgroup ->
        {
            itemgroup.add(RUNE_KENAZ);
            itemgroup.add(RUNE_GEBO);
            itemgroup.add(RUNE_JERA);
            itemgroup.add(RUNE_HAGALAZ);
            itemgroup.add(RUNE_FEHU);
            itemgroup.add(RUNE_URUZ);
            itemgroup.add(RUNE_THURISAZ);
            itemgroup.add(RUNE_ANSUZ);
            itemgroup.add(RUNE_RAIDHO);
            itemgroup.add(RUNE_WUNJO);
            itemgroup.add(RUNE_NAUTHIZ);
            itemgroup.add(RUNE_ISA);
            itemgroup.add(RUNE_EIHWAZ);
            itemgroup.add(RUNE_PERTHRO);
            itemgroup.add(RUNE_ALGIZ);
            itemgroup.add(RUNE_SOWILO);
            itemgroup.add(RUNE_TIWAZ);
            itemgroup.add(RUNE_BERKANO);
            itemgroup.add(RUNE_EHWAZ);
            itemgroup.add(RUNE_MANNAZ);
            itemgroup.add(RUNE_LAGUZ);
            itemgroup.add(RUNE_INGWAZ);
            itemgroup.add(RUNE_DAGAZ);
            itemgroup.add(RUNE_OTHALA);
        });
    }
}
