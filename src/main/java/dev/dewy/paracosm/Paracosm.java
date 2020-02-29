package dev.dewy.paracosm;

import dev.dewy.paracosm.blocks.init.ParacosmBlocks;
import dev.dewy.paracosm.items.group.ParacosmItemGroup;
import dev.dewy.paracosm.items.init.ParacosmItems;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Paracosm's main class. This is where it all begins, again.
 *
 * @author Dewy
 */
@Mod(Paracosm.MODID)
public class Paracosm
{
    // Constants
    public static final String MODID = "paracosm";
    public static final String NAME = "Paracosm";
    public static final ItemGroup ITEM_GROUP = new ParacosmItemGroup();

    public Paracosm()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.register(this);
        eventBus.register(new ParacosmBlocks());
        eventBus.register(new ParacosmItems());
    }

    // Event Handlers
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void setupCommon(FMLCommonSetupEvent event)
    {
        // Something, for now...

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void setupClient(FMLClientSetupEvent event)
    {
        // Nothing, for now...
    }
}
