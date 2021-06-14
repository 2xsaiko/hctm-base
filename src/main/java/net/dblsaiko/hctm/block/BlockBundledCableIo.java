package net.dblsaiko.hctm.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public interface BlockBundledCableIo {
    default boolean canBundledConnectTo(
        BlockState state,
        World world,
        BlockPos pos,
        Direction side,
        Direction edge
    ) {
        return false;
    }

    default short getBundledOutput(
        BlockState state,
        World world,
        BlockPos pos,
        Direction side,
        Direction edge
    ) {
        return 0;
    }

    default void onBundledInputChange(
        short data,
        BlockState state,
        World world,
        BlockPos pos,
        Direction side,
        Direction edge
    ) {}
}
