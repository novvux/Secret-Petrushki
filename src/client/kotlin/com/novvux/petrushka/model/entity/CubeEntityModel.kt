package com.novvux.petrushka.model.entity

import com.novvux.petrushka.entity.CubeEntity
import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.util.math.MatrixStack


class CubeEntityModel(root: ModelPart): EntityModel<CubeEntity?>() {
    private val bb_main: ModelPart = root.getChild("bb_main")
    override fun setAngles(entity: CubeEntity?, limbAngle: Float, limbDistance: Float, animationProgress: Float, headYaw: Float, headPitch: Float) {

    }

    override fun render(matrices: MatrixStack, vertexConsumer: VertexConsumer, light: Int, overlay: Int, color: Int) {
        bb_main.render(matrices, vertexConsumer, light, overlay, color)
    }

    companion object {
        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val bb_main = modelPartData.addChild(
                "bb_main",
                ModelPartBuilder.create().uv(0, 0).cuboid(-8.0f, -16.0f, -8.0f, 16.0f, 16.0f, 16.0f, Dilation(0.0f)),
                ModelTransform.pivot(0.0f, 24.0f, 0.0f)
            )
            return TexturedModelData.of(modelData, 64, 64)
        }
    }
}