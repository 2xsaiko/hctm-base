package net.dblsaiko.hctm.wire;

import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;

import org.jetbrains.annotations.Nullable;

import net.dblsaiko.hctm.block.BlockPartProvider;
import net.dblsaiko.hctm.common.wire.PartExt;

public record NetworkPart<T extends PartExt>(BlockPos pos, T ext) {
    public NbtCompound toTag(Block block, NbtCompound tag) {
        tag.putInt("x", this.pos.getX());
        tag.putInt("y", this.pos.getY());
        tag.putInt("z", this.pos.getZ());
        tag.put("ext", this.ext.toTag());
        tag.putString("block", Registry.BLOCK.getId(block).toString());
        return tag;
    }

    @Nullable
    public static NetworkPart<?> fromTag(NbtCompound tag) {
        Block block = Registry.BLOCK.get(new Identifier(tag.getString("block")));
        NbtElement extTag = tag.get("ext");

        if (!(block instanceof BlockPartProvider pp)) {
            return null;
        }

        PartExt ext = pp.createExtFromTag(extTag);

        if (ext == null) {
            return null;
        }

        BlockPos pos = new BlockPos(tag.getInt("x"), tag.getInt("y"), tag.getInt("z"));
        return new NetworkPart<>(pos, ext);
    }
}
