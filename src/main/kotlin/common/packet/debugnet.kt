package net.dblsaiko.hctm.common.packet

import io.netty.buffer.Unpooled
import net.dblsaiko.hctm.common.init.Packets
import net.dblsaiko.hctm.common.wire.getWireNetworkState
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

fun onDebugNetUpdateRequest(server: MinecraftServer, player: ServerPlayerEntity, handler: ServerPlayNetworkHandler, buf: PacketByteBuf, responseSender: PacketSender) {
    val dim = Identifier(buf.readString())
    val regKey = RegistryKey.of(Registry.WORLD_KEY, dim)
    val world = server.getWorld(regKey) ?: return
    val wns = world.getWireNetworkState()
    val tag = wns.writeNbt(NbtCompound())

    val out = PacketByteBuf(Unpooled.buffer())
    out.writeString(dim.toString())
    out.writeNbt(tag)
    responseSender.sendPacket(Packets.Client.DEBUG_NET_RESPONSE, out)
}