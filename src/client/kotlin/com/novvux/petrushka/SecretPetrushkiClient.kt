package com.novvux.petrushka

import com.novvux.petrushka.model.entity.CubeEntityRenderer
import com.novvux.petrushka.model.entity.CubeEntityModel
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.util.Identifier


@Environment(EnvType.CLIENT)
object SecretPetrushkiClient: ClientModInitializer {
	val MODEL_CUBE_LAYER: EntityModelLayer = EntityModelLayer(Identifier.of(SecretPetrushki.MOD_ID, "cube"), "main")
	override fun onInitializeClient() {
		// Registers our Cube Entity's renderer, which provides a model and texture for the entity.
		EntityRendererRegistry.register(SecretPetrushki.CUBE) { context -> CubeEntityRenderer(context) }
		EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData)
	}
}