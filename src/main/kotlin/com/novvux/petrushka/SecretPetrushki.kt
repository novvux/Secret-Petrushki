package com.novvux.petrushka

import com.novvux.petrushka.block.ModBlocks
import com.novvux.petrushka.entity.ModEntities
import com.novvux.petrushka.item.ModItems
import com.novvux.petrushka.particle.ModParticles
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory


object SecretPetrushki : ModInitializer {
	const val MOD_ID: String = "petrushka"
	private val logger = LoggerFactory.getLogger("petrushka")

	// Initialization all parts of mod
	override fun onInitialize() {
		ModItems.initialize()
		ModBlocks.initialize()
		ModEntities.initialize()
		//ModParticles.initialize()

		logger.info("HEWO! PETRUWKA IZ TALKIN")
	}
}