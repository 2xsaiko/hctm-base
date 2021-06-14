package net.dblsaiko.hctm.client.render.model;

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter;

import net.dblsaiko.hctm.client.HctmBaseClient;

public interface RenderPlatform {
    void spriteFix(QuadEmitter qe, int vertexIndex, int spriteIndex, float u, float v);

    static RenderPlatform getInstance() {
        return HctmBaseClient.getInstance().renderPlatform;
    }
}
