package com.novvux.petrushka

import com.novvux.petrushka.block.ModBlocks
import com.novvux.petrushka.entity.ModEntities
import com.novvux.petrushka.item.ModItems
import com.novvux.petrushka.networking.ModuleScrollPayload
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory


object SecretPetrushki : ModInitializer {
	const val MOD_ID: String = "petrushka"
	private val logger = LoggerFactory.getLogger("petrushka")
	val SCROLL_PACKET_ID: Identifier = Identifier.of(MOD_ID, "modulescroll")

	// Initialization all parts of mod
	override fun onInitialize() {
		ModItems.initialize()
		ModBlocks.initialize()
		ModEntities.initialize()

		// Scroll module packet register
		//PayloadTypeRegistry.playS2C().register(ModuleScrollPayload.ID, ModuleScrollPayload.CODEC);

		logger.info("HEWO! PETRUWKA IZ TALKIN")
	}
}