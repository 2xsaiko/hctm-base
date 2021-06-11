package net.dblsaiko.hctm.common.init

import net.dblsaiko.hctm.MOD_ID
import net.dblsaiko.hctm.client.packet.onDebugNetUpdateResponse
import net.dblsaiko.hctm.common.packet.onDebugNetUpdateRequest
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.util.Identifier

object Packets {

    object Client {

        val DEBUG_NET_RESPONSE = Identifier(MOD_ID, "debug_net_recv")

        fun register() {
            ClientPlayNetworking.registerGlobalReceiver(DEBUG_NET_RESPONSE, ::onDebugNetUpdateResponse)
        }

    }

    object Server {

        val DEBUG_NET_REQUEST = Identifier(MOD_ID, "debug_net_req")

        fun register() {
            ServerPlayNetworking.registerGlobalReceiver(DEBUG_NET_REQUEST, ::onDebugNetUpdateRequest)
        }

    }

}