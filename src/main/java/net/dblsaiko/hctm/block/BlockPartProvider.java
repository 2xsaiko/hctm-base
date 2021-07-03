package net.dblsaiko.hctm.block;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;

import org.jetbrains.annotations.Nullable;

import net.dblsaiko.hctm.common.wire.PartExt;

public interface BlockPartProvider {
    Set<PartExt> getPartsInBlock(World world, BlockPos pos, BlockState state);

    @Nullable
    PartExt createExtFromTag(NbtElement tag);
}
