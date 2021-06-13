package net.dblsaiko.hctm.init.net;

import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerboundMsgDef<T> {
    private final Identifier id;
    private final Codec<T> codec;
    private final ServerboundMsgSender<T> sender;
    private ServerMessageHandler<T> handler;

    public ServerboundMsgDef(Identifier id, Codec<T> codec) {
        this.id = id;
        this.codec = codec;
        this.sender = new ServerboundMsgSender<>(id, codec);
    }

    public void bind(ServerMessageHandler<T> handler) {
        if (this.handler != null) {
            throw new IllegalStateException("Duplicate bind for serverbound packet '%s'".formatted(this.id));
        }

        this.handler = handler;
    }

    public ServerboundMsgSender<T> sender() {
        return this.sender;
    }

    public void registerHandler() {
        var handler = this.handler;

        if (handler == null) {
            throw new IllegalStateException("Handler for packet '%s' not bound".formatted(this.id));
        }

        ServerPlayNetworking.registerGlobalReceiver(this.id, (server, player, handler1, buf, responseSender) -> {
            var message = Utils.readBuffer(this.codec, buf);
            handler.handle(server, player, handler1, message, responseSender);
        });
    }
}
