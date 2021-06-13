package net.dblsaiko.hctm.init.net;

import net.minecraft.util.Identifier;
import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

class ClientUtils {
    // needs to be in separate class due to lambda causing class loading of
    // client-only classes like MinecraftClient
    static <T> void registerHandler(Identifier id, Codec<T> codec, ClientMessageHandler<T> handler) {
        ClientPlayNetworking.registerGlobalReceiver(id, (client, handler1, buf, responseSender) -> {
            var message = Utils.readBuffer(codec, buf);
            handler.handle(client, handler1, message, responseSender);
        });
    }
}
