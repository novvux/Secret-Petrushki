package com.novvux.petrushka.render

import net.minecraft.client.render.VertexConsumer
import net.minecraft.util.math.Vec3d as Vector3d
import net.minecraft.client.render.BufferRenderer
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import org.joml.Matrix4f
import java.awt.*
import kotlin.math.*
import net.minecraft.util.math.Vec3i as Vec3

//import com.mojang.math.Axis
//import com.mojang.math.PoseStack
//import com.mojang.math.Color
//import net.minecraft.client.renderer.MultiBufferSource

object RenderUtils {
    /*fun renderSphere(mStack: PoseStack, builder: VertexConsumer, radius: Float, longs: Int, lats: Int, color: Color, alpha: Float, endU: Float) {
        val r = color.red / 255f
        val g = color.green / 255f
        val b = color.blue / 255f

        val last = mStack.last().pose()
        val startU = 0f
        val startV = 0f
        val endV = PI.toFloat()
        val stepU = (endU - startU) / longs
        val stepV = (endV - startV) / lats

        for (i in 0 until longs) {
            for (j in 0 until lats) {
                val u = i * stepU + startU
                val v = j * stepV + startV
                val un = if (i + 1 == longs) endU else (i + 1) * stepU + startU
                val vn = if (j + 1 == lats) endV else (j + 1) * stepV + startV
                val p0 = parametricSphere(u, v, radius)
                val p1 = parametricSphere(u, vn, radius)
                val p2 = parametricSphere(un, v, radius)
                val p3 = parametricSphere(un, vn, radius)

                builder.vertex(last, p1.x.toFloat(), p1.y.toFloat(), p1.z.toFloat()).color(r, g, b, alpha).endVertex()
                builder.vertex(last, p0.x.toFloat(), p0.y.toFloat(), p0.z.toFloat()).color(r, g, b, alpha).endVertex()
                builder.vertex(last, p2.x.toFloat(), p2.y.toFloat(), p2.z.toFloat()).color(r, g, b, alpha).endVertex()
                builder.vertex(last, p3.x.toFloat(), p3.y.toFloat(), p3.z.toFloat()).color(r, g, b, alpha).endVertex()
            }
        }
    }

    fun renderSphere(mStack: PoseStack?, builder: VertexConsumer?, radius: Float, longs: Int, lats: Int, color: Color?, alpha: Float) {
        renderSphere(mStack, builder!!, radius, longs, lats, color!!, alpha, PI.toFloat() * 2)
    }

    fun renderSemiSphere(mStack: PoseStack?, builder: VertexConsumer?, radius: Float, longs: Int, lats: Int, color: Color?, alpha: Float) {
        renderSphere(mStack, builder!!, radius, longs, lats, color!!, alpha, PI.toFloat())
    }

    fun parametricSphere(u: Float, v: Float, r: Float): Vec3 {
        return Vec3((cos(u) * sin(v) * r).toInt(), (cos(v) * r).toInt(), (sin(u) * sin(v) * r).toInt())
    }*/

    // Just kill me already
    /*fun renderConnectLine(posFrom: BlockPos, posTo: BlockPos, color: Color, partialTicks: Float, ms: PoseStack) {
        renderConnectLine(posFrom.center(), posTo.center(), color, partialTicks, ms)
    }

    fun renderConnectLine(from: Vec3, to: Vec3, color: Color, partialTicks: Float, ms: PoseStack) {
        val bufferDelayed: MultiBufferSource = WorldRenderHandler.getDelayedRender()

        val dX = to.x - from.x
        val dY = to.y - from.y
        val dZ = to.z - from.z

        val yaw = atan2(dZ, dX)
        val pitch = atan2(sqrt(dZ * dZ + dX * dX), dY) + PI.toFloat()

        val r = color.red / 255f
        val g = color.green / 255f
        val b = color.blue / 255f
        val a = color.alpha / 255f

        ms.pushPose()
        ms.mulPose(Direction.Axis.YP.rotationDegrees((-yaw * (180 / Math.PI)).toFloat()))
        ms.mulPose(Axis.ZP.rotationDegrees((-pitch * (180 / Math.PI) - 90f).toFloat()))
        RenderUtils.ray(ms, bufferDelayed, 0.01f, (from.distanceTo(to) + 0.01f).toFloat(), 1f, r, g, b, 0.5f * a)
        ms.popPose()
    }

    fun renderConnectLineOffset(from: Vec3, to: Vec3, color: Color, partialTicks: Float, ms: PoseStack) {
        ms.pushPose()
        ms.translate(from.x, from.y, from.z)
        renderConnectLine(from, to, color, partialTicks, ms)
        ms.popPose()
    }*/
}
