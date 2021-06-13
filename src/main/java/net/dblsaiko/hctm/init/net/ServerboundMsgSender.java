package net.dblsaiko.hctm.init.net;

import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;

public class ServerboundMsgSender<T> {
    private final Identifier id;
    private final Codec<T> codec;

    public ServerboundMsgSender(Identifier id, Codec<T> codec) {
        this.id = id;
        this.codec = codec;
    }

    public void send(T message) {
        ClientPlayNetworking.send(this.id, Utils.prepareBuffer(this.codec, message));
    }

    public void send(PacketSender sender, T message) {
        sender.sendPacket(this.id, Utils.prepareBuffer(this.codec, message));
    }
}
