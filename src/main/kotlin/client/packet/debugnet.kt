package net.dblsaiko.hctm.client.packet

import net.dblsaiko.hctm.client.wire.ClientNetworkState
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

fun onDebugNetUpdateResponse(client: MinecraftClient, handler: ClientPlayNetworkHandler, buf: PacketByteBuf, responseSender: PacketSender) {
    val dim = Identifier(buf.readString())

    val server = MinecraftClient.getInstance().server ?: return
    val type = RegistryKey.of(Registry.WORLD_KEY, dim)

    val tag = buf.readNbt()!!

    client.execute {
        ClientNetworkState.update(type, tag)
    }
}
