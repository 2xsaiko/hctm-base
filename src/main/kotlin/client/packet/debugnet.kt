package net.dblsaiko.hctm.client.packet

import net.dblsaiko.hctm.client.wire.ClientNetworkState
import net.fabricmc.fabric.api.network.PacketContext
import net.minecraft.client.MinecraftClient
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey

fun onDebugNetUpdateResponse(context: PacketContext, buffer: PacketByteBuf) {
  val dim = Identifier(buffer.readString())

  val server = MinecraftClient.getInstance().server ?: return
  val type = RegistryKey.of(Registry.DIMENSION, dim)

  val tag = buffer.readCompoundTag()!!

  context.taskQueue.execute {
    ClientNetworkState.update(type, tag)
  }
}
