package dev.dewy.paracosm.blocks.init;

import dev.dewy.paracosm.Paracosm;
import dev.dewy.paracosm.blocks.*;
import dev.dewy.paracosm.items.BasicBlockItem;
import dev.dewy.paracosm.items.init.ParacosmItems;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
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

    // Dev & Test Blocks
    public static final RegistryObject<BasicBlock> PP_SMOL_BLOCK = registerBlock("pp_smol_block", () -> new BasicBlock(Block.Properties.create(Material.ORGANIC).sound(SoundType.ANVIL).lightValue(8).hardnessAndResistance(3.0F, 3.0F).harvestLevel(3).harvestTool(ToolType.PICKAXE).slipperiness(99.0F)));

    public static final RegistryObject<BasicBlock> PARACOSMIC_DIRT = registerBlock("paracosmic_dirt", () -> new BasicBlock(Block.Properties.create(Material.EARTH, MaterialColor.PURPLE).hardnessAndResistance(0.7F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<BasicBlock> PARACOSMIC_COARSE_DIRT = registerBlock("paracosmic_coarse_dirt", () -> new BasicBlock(Block.Properties.create(Material.EARTH, MaterialColor.PURPLE).hardnessAndResistance(0.7F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<ParacosmicGrandBlock> PARACOSMIC_SAND = registerBlock("paracosmic_sand", () -> new ParacosmicGrandBlock(Block.Properties.create(Material.SAND, MaterialColor.PURPLE).hardnessAndResistance(0.5F).sound(SoundType.SAND).harvestTool(ToolType.SHOVEL), 14406560)); // TODO: Change this later to the actual color. And the gravel one too.
    public static final RegistryObject<ParacosmicGrandBlock> PARACOSMIC_GRAVEL = registerBlock("paracosmic_gravel", () -> new ParacosmicGrandBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.6F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL), -8356741));

    public static final RegistryObject<ParacosmicLogBlock> CORRUPTED_LOG = registerBlock("corrupted_log", () -> new ParacosmicLogBlock(Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE), MaterialColor.WOOD));
    public static final RegistryObject<ParacosmicLogBlock> WINDSWEPT_LOG = registerBlock("windswept_log", () -> new ParacosmicLogBlock(Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE), MaterialColor.WOOD));
    public static final RegistryObject<ParacosmicLogBlock> PARACOSMIC_LOG = registerBlock("paracosmic_log", () -> new ParacosmicLogBlock(Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE), MaterialColor.WOOD));
    public static final RegistryObject<BasicBlock> CORRUPTED_PLANKS = registerBlock("corrupted_planks", () -> new BasicBlock(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<BasicBlock> WINDSWEPT_PLANKS = registerBlock("windswept_planks", () -> new BasicBlock(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));
    public static final RegistryObject<BasicBlock> PARACOSMIC_PLANKS = registerBlock("paracosmic_planks", () -> new BasicBlock(Block.Properties.create(Material.WOOD, MaterialColor.PURPLE).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).harvestTool(ToolType.AXE)));

    public static final RegistryObject<BasicBlock> PARACOSMIC_SNOW = registerBlock("paracosmic_snow", () -> new BasicBlock(Block.Properties.create(Material.SNOW_BLOCK).hardnessAndResistance(0.2F).sound(SoundType.SNOW).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<SnowLayerBlock> PARACOSMIC_SNOW_LAYER = registerBlock("paracosmic_snow_layer", () -> new SnowLayerBlock(Block.Properties.create(Material.SNOW).tickRandomly().hardnessAndResistance(0.1F).sound(SoundType.SNOW).harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<IceBlock> PARACOSMIC_ICE = registerBlock("paracosmic_ice", () -> new IceBlock(Block.Properties.create(Material.ICE).slipperiness(0.98F).tickRandomly().hardnessAndResistance(0.5F).sound(SoundType.GLASS).notSolid().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<BasicBlock> PARACOSMIC_PACKED_ICE = registerBlock("paracosmic_packed_ice", () -> new BasicBlock(Block.Properties.create(Material.PACKED_ICE).slipperiness(0.98F).hardnessAndResistance(0.5F).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE)));

    // TODO: Compressed blocks. cba rn
    public static final RegistryObject<BasicBlock> COMPRESSED_PLATINUM_BLOCK = registerBlock("compressed_platinum_block", () -> new BasicBlock(Block.Properties.create(Material.IRON, MaterialColor.IRON).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL).harvestTool(ToolType.PICKAXE)));

    @SubscribeEvent
    public void onRegisterBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> registry = event.getRegistry();

        BLOCK_REGISTRY.stream().map(Supplier::get).forEach(registry::register);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String blockName, Supplier<T> block)
    {
        return registerBlock(blockName, block, b -> () -> new BasicBlockItem(b.get(), p -> p.group(Paracosm.PARACOSM_ITEM_GROUP)));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String blockName, Supplier<T> block, Function<RegistryObject<T>, Supplier<? extends BlockItem>> blockItem)
    {
        ResourceLocation blockLocation = new ResourceLocation(Paracosm.MODID, blockName);
        BLOCK_REGISTRY.add(() -> block.get().setRegistryName(blockLocation));
        RegistryObject<T> reg = RegistryObject.of(blockLocation, ForgeRegistries.BLOCKS);

        ParacosmItems.ITEMBLOCK_REGISTRY.add(() -> blockItem.apply(reg).get().setRegistryName(blockLocation));

        return reg;
    }
}