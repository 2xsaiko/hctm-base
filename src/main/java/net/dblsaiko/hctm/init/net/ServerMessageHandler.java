package net.dblsaiko.hctm.init.net;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.fabricmc.fabric.api.networking.v1.PacketSender;

@FunctionalInterface
public interface ServerMessageHandler<T> {
    void handle(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, T message, PacketSender sender);
}
