package dev.dewy.paracosm.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ParacosmicLogBlock extends RotatedPillarBlock
{
    private final MaterialColor verticalColor;

    public ParacosmicLogBlock(Properties properties, MaterialColor materialColor)
    {
        super(properties);

        this.verticalColor = materialColor;
    }

    public MaterialColor getMaterialColor(BlockState state, IBlockReader world, BlockPos pos)
    {
        return state.get(AXIS) == Direction.Axis.Y ? this.verticalColor : this.materialColor;
    }
}
