package com.novvux.petrushka.model.entity

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.SecretPetrushkiClient
import com.novvux.petrushka.entity.CubeEntity
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.MobEntityRenderer
import net.minecraft.util.Identifier

class CubeEntityRenderer(context: EntityRendererFactory.Context) : MobEntityRenderer<CubeEntity, CubeEntityModel>
    (context, CubeEntityModel(context.getPart(SecretPetrushkiClient.MODEL_CUBE_LAYER)), 0.5f) {
    override fun getTexture(entity: CubeEntity?): Identifier {
        return Identifier.of(SecretPetrushki.MOD_ID, "textures/entity/cube.png")
    }
}