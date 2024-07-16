package com.novvux.petrushka

import com.novvux.petrushka.item.ModItems
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object SecretPetrushki : ModInitializer {
	const val MOD_ID: String = "petrushka"
	private val logger = LoggerFactory.getLogger("petrushka")

	override fun onInitialize() {
		ModItems.initialize();
		logger.info("HEWO! PETRUWKA IZ TALKIN")
	}
}