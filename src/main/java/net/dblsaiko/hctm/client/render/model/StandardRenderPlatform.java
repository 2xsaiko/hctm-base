package net.dblsaiko.hctm.client.render.model;

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;

public class StandardRenderPlatform implements RenderPlatform {
    public static final StandardRenderPlatform INSTANCE = new StandardRenderPlatform();

    private StandardRenderPlatform() {}

    @Override
    public void spriteFix(QuadEmitter qe, int vertexIndex, int spriteIndex, float u, float v) {
        qe.sprite(vertexIndex, spriteIndex, u, v);
    }
}
