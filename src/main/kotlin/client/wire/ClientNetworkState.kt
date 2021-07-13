package net.dblsaiko.hctm.client.wire

import net.dblsaiko.hctm.client.HctmBaseClient
import net.dblsaiko.hctm.common.wire.WireNetworkController
import net.dblsaiko.hctm.net.DebugNetRequest
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.World

object ClientNetworkState {

    private val caches = mutableMapOf<RegistryKey<World>, Entry>()

    fun request(world: World): WireNetworkController? {
        if (world !is ServerWorld) error("Yeah let's not do that.")

        val worldKey = world.registryKey

        if (caches[worldKey]?.isExpired() != false) {
            HctmBaseClient.getInstance().clientNetHandler.debugNetRequest.send(DebugNetRequest(worldKey.value))
        }

        return caches[worldKey]?.controller
    }

    fun update(dt: RegistryKey<World>, tag: NbtCompound) {
        caches[dt] = Entry(WireNetworkController.fromTag(tag))
    }

}

private data class Entry(val controller: WireNetworkController, val created: Long = utime()) {
    fun isExpired() = utime() - created > 1
}

private fun utime() = System.currentTimeMillis() / 1000