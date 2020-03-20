package dev.dewy.paracosm.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Grand is a combination of gravel and sand. Refers to blocks like gravel and sand, but not like anvils and TNT.
 */
public class ParacosmicGrandBlock extends FallingBlock
{
    private final int dustColor;

    public ParacosmicGrandBlock(Properties properties, int dustColor)
    {
        super(properties);

        this.dustColor = dustColor;
    }

    @OnlyIn(Dist.CLIENT)
    public int getDustColor(BlockState dustColor)
    {
        return this.dustColor;
    }
}
