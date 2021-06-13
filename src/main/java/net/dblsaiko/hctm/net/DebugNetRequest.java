package net.dblsaiko.hctm.net;

import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DebugNetRequest(Identifier world) {
    public static Codec<DebugNetRequest> CODEC = RecordCodecBuilder.create(instance -> instance
        .group(Identifier.CODEC.fieldOf("world").forGetter(DebugNetRequest::world))
        .apply(instance, DebugNetRequest::new)
    );
}
