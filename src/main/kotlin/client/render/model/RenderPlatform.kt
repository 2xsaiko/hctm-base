package net.dblsaiko.hctm.client.render.model

import net.fabricmc.fabric.api.renderer.v1.mesh.QuadEmitter
import grondag.canvas.apiimpl.mesh.MutableQuadViewImpl as CanvasMutableQuadViewImpl

interface RenderPlatform {

  fun spriteFix(qe: QuadEmitter, vertexIndex: Int, spriteIndex: Int, u: Float, v: Float)

  companion object {
    var active: RenderPlatform = StandardRenderPlatform
  }

}

fun QuadEmitter.spriteFix(vertexIndex: Int, spriteIndex: Int, u: Float, v: Float) {
  RenderPlatform.active.spriteFix(this, vertexIndex, spriteIndex, u, v)
}

object StandardRenderPlatform : RenderPlatform {

  override fun spriteFix(qe: QuadEmitter, vertexIndex: Int, spriteIndex: Int, u: Float, v: Float) {
    qe.sprite(vertexIndex, spriteIndex, u, v)
  }

}

object CanvasRenderPlatform : RenderPlatform {

  override fun spriteFix(qe: QuadEmitter, vertexIndex: Int, spriteIndex: Int, u: Float, v: Float) {
    qe as CanvasMutableQuadViewImpl
    qe.spriteFloat(vertexIndex, spriteIndex, u, v)
  }

}