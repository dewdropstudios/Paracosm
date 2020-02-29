package dev.dewy.paracosm.blocks.init;

import dev.dewy.paracosm.Paracosm;
import dev.dewy.paracosm.blocks.BasicBlock;
import dev.dewy.paracosm.items.BasicBlockItem;
import dev.dewy.paracosm.items.init.ParacosmItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ParacosmBlocks
{
    private static final List<Supplier<? extends Block>> BLOCK_REGISTRY = new ArrayList<>();

    public static final RegistryObject<BasicBlock> PP_SMOL_BLOCK = register("pp_smol_block", () -> new BasicBlock(Material.ROCK, SoundType.STONE, 4.0F, 6.0F));

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();

        BLOCK_REGISTRY.stream().map(Supplier::get).forEach(registry::register);
    }

    private static <T extends Block> RegistryObject<T> register(String blockName, Supplier<T> block)
    {
        return register(blockName, block, b -> () -> new BasicBlockItem(b.get(), p -> p.group(Paracosm.ITEM_GROUP)));
    }

    private static <T extends Block> RegistryObject<T> register(String blockName, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> blockItem)
    {
        ResourceLocation blockLocation = new ResourceLocation(Paracosm.MODID, blockName);
        BLOCK_REGISTRY.add(() -> block.get().setRegistryName(blockLocation));
        RegistryObject<T> reg = RegistryObject.of(blockLocation, ForgeRegistries.BLOCKS);

        ParacosmItems.ITEMBLOCK_REGISTRY.add(() -> blockItem.apply(reg).get().setRegistryName(blockLocation));

        return reg;
    }
}