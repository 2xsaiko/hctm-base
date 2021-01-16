package net.dblsaiko.hctm.client

import net.dblsaiko.hctm.client.render.model.CanvasRenderPlatform
import net.dblsaiko.hctm.client.render.model.RenderPlatform
import net.dblsaiko.hctm.common.init.Packets
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader

object HCTMClient : ClientModInitializer {

  override fun onInitializeClient() {
    Packets.Client.register()

    if (FabricLoader.getInstance().isModLoaded("canvas")) {
      RenderPlatform.active = CanvasRenderPlatform
    }
  }

}