package net.dblsaiko.hctm.client;

import net.fabricmc.loader.api.FabricLoader;

import java.util.Objects;

import net.dblsaiko.hctm.HctmBase;
import net.dblsaiko.hctm.client.render.model.CanvasRenderPlatform;
import net.dblsaiko.hctm.client.render.model.RenderPlatform;
import net.dblsaiko.hctm.client.render.model.StandardRenderPlatform;
import net.dblsaiko.hctm.net.ClientNetHandler;

public class HctmBaseClient {
    private static HctmBaseClient INSTANCE;

    public final ClientNetHandler clientNetHandler;

    public final RenderPlatform renderPlatform;

    private HctmBaseClient(HctmBase b) {
        this.clientNetHandler = new ClientNetHandler(b.packets);

        if (FabricLoader.getInstance().isModLoaded("canvas")) {
            this.renderPlatform = CanvasRenderPlatform.INSTANCE;
        } else {
            this.renderPlatform = StandardRenderPlatform.INSTANCE;
        }
    }

    public static void initialize() {
        HctmBaseClient client = new HctmBaseClient(HctmBase.getInstance());
        client.clientNetHandler.register();
        INSTANCE = client;
    }

    public static HctmBaseClient getInstance() {
        return Objects.requireNonNull(INSTANCE);
    }
}
