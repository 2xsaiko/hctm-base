package net.dblsaiko.hctm.client.render.model;

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;

import grondag.canvas.apiimpl.mesh.MutableQuadViewImpl;

public class CanvasRenderPlatform implements RenderPlatform {
    public static final CanvasRenderPlatform INSTANCE = new CanvasRenderPlatform();

    private CanvasRenderPlatform() {}

    @Override
    public void spriteFix(QuadEmitter qe, int vertexIndex, int spriteIndex, float u, float v) {
        ((MutableQuadViewImpl) qe).spriteFloat(vertexIndex, u, v);
    }
}
