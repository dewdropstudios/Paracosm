package dev.dewy.paracosm.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.PushReaction;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class IceBlock extends BreakableBlock
{
    public IceBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public void harvestBlock(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity tileEntity, ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, tileEntity, stack);

        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) == 0)
        {
            if (world.dimension.doesWaterVaporize())
            {
                world.removeBlock(pos, false);

                return;
            }

            Material materialBelow = world.getBlockState(pos.down()).getMaterial();

            if (materialBelow.blocksMovement() || materialBelow.isLiquid())
            {
                world.setBlockState(pos, Blocks.WATER.getDefaultState());
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
    {
        if (world.getLightFor(LightType.BLOCK, pos) > 11 - state.getOpacity(world, pos))
        {
            this.becomeWater(state, world, pos);
        }
    }

    private void becomeWater(BlockState state, World world, BlockPos pos)
    {
        if (world.dimension.doesWaterVaporize())
        {
            world.removeBlock(pos, false);
        }
        else
        {
            world.setBlockState(pos, Blocks.WATER.getDefaultState());
            world.neighborChanged(pos, Blocks.WATER, pos);
        }
    }

    @Override
    public PushReaction getPushReaction(BlockState state)
    {
        return PushReaction.NORMAL;
    }
}
