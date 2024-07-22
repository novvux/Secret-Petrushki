package com.novvux.petrushka

import com.novvux.petrushka.entity.ModEntitiesClient
import com.novvux.petrushka.particle.ModParticlesClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment

@Environment(EnvType.CLIENT)
object SecretPetrushkiClient: ClientModInitializer {
	override fun onInitializeClient() {
		// Initialize client renderers
		ModEntitiesClient.initialize()
		ModParticlesClient.initialize()
	}
}