package net.dblsaiko.hctm.init.net;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ClientboundMsgSender<T> {
    private final Identifier id;
    private final Codec<T> codec;

    public ClientboundMsgSender(Identifier id, Codec<T> codec) {
        this.id = id;
        this.codec = codec;
    }

    public void send(ServerPlayerEntity player, T message) {
        ServerPlayNetworking.send(player, this.id, Utils.prepareBuffer(this.codec, message));
    }

    public void send(PacketSender sender, T message) {
        sender.sendPacket(this.id, Utils.prepareBuffer(this.codec, message));
    }
}
