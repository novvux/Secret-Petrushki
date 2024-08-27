package com.novvux.petrushka

import com.novvux.petrushka.entity.ModEntities
import com.novvux.petrushka.item.ModItems
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


class SecretPetrushki: ModInitializer {
	companion object {
		fun of(path: String?): Identifier {
			return Identifier.of(MOD_ID, path)
		}

		const val MOD_ID: String = "petrushka"
		private val logger = LoggerFactory.getLogger("petrushka")
	}

	// Initialization all parts of mod
	override fun onInitialize() {
		ModItems.initialize()
		ModEntities.initialize()


		logger.info("HEWO! PETRUWKA IZ TALKIN")
	}
}