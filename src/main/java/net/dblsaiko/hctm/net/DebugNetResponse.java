package net.dblsaiko.hctm.net;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DebugNetResponse(Identifier world, NbtCompound data) {
    public static final Codec<DebugNetResponse> CODEC = RecordCodecBuilder.create(instance -> instance
        .group(
            Identifier.CODEC.fieldOf("world").forGetter(DebugNetResponse::world),
            NbtCompound.CODEC.fieldOf("data").forGetter(DebugNetResponse::data)
        ).apply(instance, DebugNetResponse::new)
    );
}
