package net.dblsaiko.hctm.init.net;

import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;

public class ServerboundMsgSender<T> {
    private final Identifier id;
    private final Codec<T> codec;

    public ServerboundMsgSender(Identifier id, Codec<T> codec) {
        this.id = id;
        this.codec = codec;
    }

    public void send(T message) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            throw new IllegalStateException("Can't send serverbound message on dedicated server");
        }

        ClientPlayNetworking.send(this.id, Utils.prepareBuffer(this.codec, message));
    }

    public void send(PacketSender sender, T message) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            throw new IllegalStateException("Can't send serverbound message on dedicated server");
        }

        sender.sendPacket(this.id, Utils.prepareBuffer(this.codec, message));
    }
}
