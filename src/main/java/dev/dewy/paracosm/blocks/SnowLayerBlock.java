package dev.dewy.paracosm.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class SnowLayerBlock extends Block
{
    private static final IntegerProperty LAYERS;
    private static final VoxelShape[] SHAPES;

    public SnowLayerBlock(Properties properties)
    {
        super(properties);

        this.setDefaultState(this.stateContainer.getBaseState().with(LAYERS, 1));
    }

    @Override
    public boolean allowsMovement(BlockState state, IBlockReader reader, BlockPos pos, PathType pathType)
    {
        if (pathType == PathType.LAND)
        {
            return state.get(LAYERS) < 5;
        }

        return false;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPES[state.get(LAYERS)];
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPES[state.get(LAYERS) - 1];
    }

    @Override
    public boolean isTransparent(BlockState state)
    {
        return true;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader reader, BlockPos pos)
    {
        BlockState belowState = reader.getBlockState(pos.down());
        Block blockBelow = belowState.getBlock();

        if (blockBelow != Blocks.ICE && blockBelow != Blocks.PACKED_ICE && blockBelow != Blocks.BARRIER)
        {
            if (blockBelow != Blocks.HONEY_BLOCK && blockBelow != Blocks.SOUL_SAND)
            {
                return Block.doesSideFillSquare(belowState.getCollisionShape(reader, pos.down()), Direction.UP) || blockBelow == this && belowState.get(LAYERS) == 8;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
    {
        return !state.isValidPosition(world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
    }

    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand)
    {
        if (world.getLightFor(LightType.BLOCK, pos) > 11)
        {
            spawnDrops(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    public boolean isReplaceable(BlockState state, BlockItemUseContext context)
    {
        int layerCount = state.get(LAYERS);

        if (context.getItem().getItem() == this.asItem() && layerCount < 8)
        {
            if (context.replacingClickedOnBlock())
            {
                return context.getFace() == Direction.UP;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return layerCount == 1;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
        BlockState snowLayerBlockState = context.getWorld().getBlockState(context.getPos());

        if (snowLayerBlockState.getBlock() == this)
        {
            int layers = snowLayerBlockState.get(LAYERS);

            return snowLayerBlockState.with(LAYERS, Math.min(8, layers + 1));
        }
        else
        {
            return super.getStateForPlacement(context);
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateContainer)
    {
        stateContainer.add(LAYERS);
    }

    static
    {
        LAYERS = BlockStateProperties.LAYERS_1_8;
        SHAPES = new VoxelShape[]{VoxelShapes.empty(), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D), Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)};
    }
}
