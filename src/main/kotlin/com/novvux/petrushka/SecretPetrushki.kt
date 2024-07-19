package com.novvux.petrushka

import com.novvux.petrushka.block.ModBlocks
import com.novvux.petrushka.entity.CubeEntity
import com.novvux.petrushka.item.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.mob.MobEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.World
import org.slf4j.LoggerFactory

object SecretPetrushki : ModInitializer {
	const val MOD_ID: String = "petrushka"
	private val logger = LoggerFactory.getLogger("petrushka")

	val CUBE: EntityType<CubeEntity> = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "cube"),
		FabricEntityTypeBuilder.create<CubeEntity?>(SpawnGroup.CREATURE)
		{ entityType: EntityType<CubeEntity?>?, world: World? -> CubeEntity(entityType, world) }
			.dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	)

	override fun onInitialize() {
		ModItems.initialize()
		ModBlocks.initialize()
		//ModEntities.initialize()
		FabricDefaultAttributeRegistry.register(CUBE, MobEntity.createMobAttributes())


		logger.info("HEWO! PETRUWKA IZ TALKIN")
	}
}