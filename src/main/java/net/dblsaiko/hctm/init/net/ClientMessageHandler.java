package net.dblsaiko.hctm.init.net;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;

@FunctionalInterface
public interface ClientMessageHandler<T> {
    void handle(MinecraftClient client, ClientPlayNetworkHandler handler, T message, PacketSender sender);
}
