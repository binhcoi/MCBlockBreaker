package com.binhcoi.mcmods.blockbreaker;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BlockBreakerBlock extends FacingBlock {
    public static final BooleanProperty TRIGGERED = Properties.TRIGGERED;

    protected BlockBreakerBlock(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.FACING, Direction.NORTH).with(TRIGGERED,false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.FACING, TRIGGERED);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos,
            boolean notify) {
        boolean bl = world.isReceivingRedstonePower(pos);
        boolean bl2 = (Boolean) state.get(TRIGGERED);
        if (bl && !bl2) {
            world.getBlockTickScheduler().schedule(pos, this, 4);
            world.setBlockState(pos, (BlockState) state.with(TRIGGERED, true), 4);
        } else if (!bl && bl2) {
            world.setBlockState(pos, (BlockState) state.with(TRIGGERED, false), 4);
        }

    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.breakBlock(state, world, pos);
    }

    public void breakBlock(BlockState state, ServerWorld world, BlockPos pos) {
        BlockPos offsetPos = pos.offset((Direction) state.get(FACING));
        world.breakBlock(offsetPos, true);
    }
}
