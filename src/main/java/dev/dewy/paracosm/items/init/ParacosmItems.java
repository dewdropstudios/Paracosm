package dev.dewy.paracosm.items.init;

import dev.dewy.paracosm.Paracosm;
import dev.dewy.paracosm.items.BasicItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ParacosmItems
{
    private static final List<Supplier<? extends Item>> ITEM_REGISTRY = new ArrayList<>();

    private static <T extends Item> RegistryObject<T> registerItem(String itemName)
    {
        return registerItem(itemName, () -> new BasicItem(item -> item.group(Paracosm.ITEM_GROUP)));
    }

    private static <T extends Item> RegistryObject<T> registerItem(String itemName, Supplier<? extends Item> item)
    {
        ResourceLocation itemLocation = new ResourceLocation(Paracosm.MODID, itemName);
        ITEM_REGISTRY.add(() -> item.get().setRegistryName(itemName));

        return RegistryObject.of(itemLocation, ForgeRegistries.ITEMS);
    }
}
