package net.dblsaiko.hctm.init.net;

import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtTagSizeTracker;
import net.minecraft.nbt.NbtTypes;
import net.minecraft.network.PacketByteBuf;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

class Utils {
    static <T> PacketByteBuf prepareBuffer(Codec<T> codec, T message) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        DataResult<NbtElement> res = codec.encodeStart(NbtOps.INSTANCE, message);
        NbtElement el = res.getOrThrow(false, r -> {});
        buf.writeByte(el.getType());

        try {
            el.write(new DataOutputStream(new ByteBufOutputStream(buf)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return buf;
    }

    static <T> T readBuffer(Codec<T> codec, PacketByteBuf buf) {
        var type = buf.readByte();
        NbtElement read;

        try {
            read = NbtTypes.byId(type).read(new DataInputStream(new ByteBufInputStream(buf)), 0, new NbtTagSizeTracker(buf.readableBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return codec.parse(NbtOps.INSTANCE, read).getOrThrow(false, e -> {});
    }
}
