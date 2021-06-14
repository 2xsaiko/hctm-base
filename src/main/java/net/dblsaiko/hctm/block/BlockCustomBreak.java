package net.dblsaiko.hctm.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import org.jetbrains.annotations.Nullable;

public interface BlockCustomBreak {
    boolean tryBreak(
        BlockState state,
        BlockPos pos,
        World world,
        PlayerEntity player,
        @Nullable BlockEntity blockEntity
    );
}
