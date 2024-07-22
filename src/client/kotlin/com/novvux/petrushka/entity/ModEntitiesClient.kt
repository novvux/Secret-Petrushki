package com.novvux.petrushka.entity

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.model.entity.CubeEntityModel
import com.novvux.petrushka.render.entity.CubeEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.util.Identifier

object ModEntitiesClient {
    val MODEL_CUBE_LAYER: EntityModelLayer = EntityModelLayer(Identifier.of(SecretPetrushki.MOD_ID, "cube"), "main")
    fun initialize() {
        // Registers our Cube Entity's renderer, which provides a model and texture for the entity.
        EntityRendererRegistry.register(ModEntities.CUBE) { context -> CubeEntityRenderer(context) }
        EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData)
    }
}