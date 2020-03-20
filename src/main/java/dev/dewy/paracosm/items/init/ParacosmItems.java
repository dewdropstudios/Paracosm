package dev.dewy.paracosm.items.init;

import dev.dewy.paracosm.Paracosm;
import dev.dewy.paracosm.items.BasicItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ParacosmItems
{
    private static final List<Supplier<? extends Item>> ITEM_REGISTRY = new ArrayList<>();
    public static final List<Supplier<? extends Item>> ITEMBLOCK_REGISTRY = new ArrayList<>();

    public static final RegistryObject<BasicItem> PP_SMOL_ITEM = registerItem("pp_smol");

    public static final RegistryObject<BasicItem> PLATINUM_INGOT = registerItem("platinum_ingot");
    public static final RegistryObject<BasicItem> TUNGSTEN_INGOT = registerItem("tungsten_ingot");
    public static final RegistryObject<BasicItem> COBALT_INGOT = registerItem("cobalt_ingot");
    public static final RegistryObject<BasicItem> TITANIUM_INGOT = registerItem("titanium_ingot");
    public static final RegistryObject<BasicItem> THULIUM_SHARD = registerItem("thulium_shard");
    public static final RegistryObject<BasicItem> CHROMIUM_SHARD = registerItem("chromium_shard");
    public static final RegistryObject<BasicItem> CHR_THU_NANOTHREAD = registerItem("nanothread");

    public static final RegistryObject<BasicItem> COMPRESSED_PLATINUM = registerItem("compressed_platinum");
    public static final RegistryObject<BasicItem> COMPRESSED_TUNGSTEN = registerItem("compressed_tungsten");
    public static final RegistryObject<BasicItem> COMPRESSED_COBALT = registerItem("compressed_cobalt");
    public static final RegistryObject<BasicItem> COMPRESSED_TITANIUM = registerItem("compressed_titanium");
    public static final RegistryObject<BasicItem> COMPRESSED_THULIUM = registerItem("compressed_thulium");
    public static final RegistryObject<BasicItem> COMPRESSED_CHROMIUM = registerItem("compressed_chromium");

    public static final RegistryObject<BasicItem> FLIGHT_ESSENCE = registerItem("flight_essence");
    public static final RegistryObject<BasicItem> LIGHT_ESSENCE = registerItem("light_essence");
    public static final RegistryObject<BasicItem> NIGHT_ESSENCE = registerItem("night_essence");
    public static final RegistryObject<BasicItem> FRIGHT_ESSENCE = registerItem("fright_essence");
    public static final RegistryObject<BasicItem> SIGHT_ESSENCE = registerItem("sight_essence");
    public static final RegistryObject<BasicItem> CHAOS_ESSENCE = registerItem("chaos_essence");

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> registry = event.getRegistry();

        ITEMBLOCK_REGISTRY.stream().map(Supplier::get).forEach(registry::register);
        ITEM_REGISTRY.stream().map(Supplier::get).forEach(registry::register);
    }

    private static <T extends Item> RegistryObject<T> registerItem(String itemName)
    {
        return registerItem(itemName, () -> new BasicItem(item -> item.group(Paracosm.PARACOSM_ITEM_GROUP)));
    }

    private static <T extends Item> RegistryObject<T> registerItem(String itemName, Supplier<? extends Item> item)
    {
        ResourceLocation itemLocation = new ResourceLocation(Paracosm.MODID, itemName);
        ITEM_REGISTRY.add(() -> item.get().setRegistryName(itemName));

        return RegistryObject.of(itemLocation, ForgeRegistries.ITEMS);
    }
}
