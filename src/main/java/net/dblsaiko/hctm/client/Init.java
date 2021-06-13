package net.dblsaiko.hctm.client;

import net.fabricmc.loader.api.FabricLoader;

import net.dblsaiko.hctm.HctmBase;
import net.dblsaiko.hctm.client.render.model.CanvasRenderPlatform;
import net.dblsaiko.hctm.client.render.model.RenderPlatform;

public class Init {
    private Init() {
    }

    public static void initialize() {
        HctmBase.getInstance().clientNetHandler.register();

        if (FabricLoader.getInstance().isModLoaded("canvas")) {
            RenderPlatform.Companion.setActive(CanvasRenderPlatform.INSTANCE);
        }
    }
}
