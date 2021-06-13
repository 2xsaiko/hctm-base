package net.dblsaiko.hctm.net;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.fabricmc.fabric.api.networking.v1.PacketSender;

import net.dblsaiko.hctm.client.wire.ClientNetworkState;
import net.dblsaiko.hctm.init.net.ServerboundMsgSender;

public class ClientNetHandler {
    private final Packets packets;

    public final ServerboundMsgSender<DebugNetRequest> debugNetRequest;

    public ClientNetHandler(Packets packets) {
        this.packets = packets;

        this.debugNetRequest = packets.debugNetRequest.sender();
        packets.debugNetResponse.bind(this::handleDebugNetResponse);
    }

    private void handleDebugNetResponse(MinecraftClient client, ClientPlayNetworkHandler handler, DebugNetResponse msg, PacketSender responseSender) {
        var world = RegistryKey.of(Registry.WORLD_KEY, msg.world());
        client.execute(() -> ClientNetworkState.INSTANCE.update(world, msg.data()));
    }

    public void register() {
        this.packets.registerClientHandlers();
    }
}
