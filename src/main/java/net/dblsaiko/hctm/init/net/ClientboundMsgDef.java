package net.dblsaiko.hctm.init.net;

import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class ClientboundMsgDef<T> {
    private final Identifier id;
    private final Codec<T> codec;
    private final ClientboundMsgSender<T> sender;
    private ClientMessageHandler<T> handler;

    public ClientboundMsgDef(Identifier id, Codec<T> codec) {
        this.id = id;
        this.codec = codec;
        this.sender = new ClientboundMsgSender<>(id, codec);
    }

    public void bind(ClientMessageHandler<T> handler) {
        if (this.handler != null) {
            throw new IllegalStateException("Duplicate bind for clientbound packet '%s'".formatted(this.id));
        }

        this.handler = handler;
    }

    public ClientboundMsgSender<T> sender() {
        return this.sender;
    }

    public void registerHandler() {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
            throw new IllegalStateException("Can't register clientbound message on dedicated server");
        }

        var handler = this.handler;

        if (handler == null) {
            throw new IllegalStateException("Handler for packet '%s' not bound".formatted(this.id));
        }

        ClientUtils.registerHandler(this.id, this.codec, handler);
    }
}
