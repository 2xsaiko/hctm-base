package net.dblsaiko.hctm.net;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.fabricmc.fabric.api.networking.v1.PacketSender;

import net.dblsaiko.hctm.common.wire.WireNetworkStateKt;
import net.dblsaiko.hctm.init.net.ClientboundMsgSender;

public class ServerNetHandler {
    private final Packets packets;

    public final ClientboundMsgSender<DebugNetResponse> debugNetResponse;

    public ServerNetHandler(Packets packets) {
        this.packets = packets;

        packets.debugNetRequest.bind(this::handleDebugNetRequest);
        this.debugNetResponse = packets.debugNetResponse.sender();
    }

    private void handleDebugNetRequest(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, DebugNetRequest msg, PacketSender responseSender) {
        var worldKey = RegistryKey.of(Registry.WORLD_KEY, msg.world());
        var world = server.getWorld(worldKey);

        if (world == null) {
            return;
        }

        var wns = WireNetworkStateKt.getWireNetworkState(world);
        var nbt = wns.getController().toTag(world);

        this.debugNetResponse.send(responseSender, new DebugNetResponse(msg.world(), nbt));
    }

    public void register() {
        this.packets.registerServerHandlers();
    }
}
