package com.arja.runeforge.item;

import com.arja.runeforge.Runeforge;
import com.arja.runeforge.datagen.ModItemTagProvider;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems
{
    public static final Item KEBAB = registerItem("kebab", Item::new, new Item.Settings()
            .food(ModFoodComponents.KEBAB_FOOD_COMPONENT, ModFoodComponents.KEBAB_CONSUMABLE_COMPONENT));

    public static final RegistryKey<ItemGroup> MAGIC_TOOLS_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            Identifier.of(Runeforge.MOD_ID, "magic_tools"));
    public static final ItemGroup MAGIC_TOOLS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(KEBAB))
            .displayName(Text.translatable("itemgroup.magic_tools"))
            .build();

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

        Registry.register(Registries.ITEM_GROUP, MAGIC_TOOLS_ITEM_GROUP_KEY, MAGIC_TOOLS_ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(MAGIC_TOOLS_ITEM_GROUP_KEY).register(itemgroup ->
        {
            itemgroup.add(KEBAB);
        });
    }
}
