package net.dblsaiko.hctm.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import org.jetbrains.annotations.Nullable;

public interface BlockAdvancedShape {
    @Nullable
    BlockHitResult rayTrace(BlockState state, BlockPos pos, Vec3d from, Vec3d to);
}
