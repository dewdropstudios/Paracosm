package dev.dewy.paracosm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.function.Function;

public class BasicBlock extends Block
{
    public BasicBlock(Material mat, Function<Properties, Properties> properties)
    {
        super(properties.apply(Properties.create(mat)));
    }

    public BasicBlock(Material mat, SoundType sound, float hardness, float resistance)
    {
        super(Properties.create(mat).sound(sound).hardnessAndResistance(hardness, resistance));
    }
}
