package com.novvux.petrushka.entity

import com.novvux.petrushka.SecretPetrushki
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.*
import net.minecraft.entity.mob.MobEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.world.World


object ModEntities {
    @Suppress("DEPRECATION")
    val CUBE: EntityType<CubeEntity> = Registry.register(
        Registries.ENTITY_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "cube"),
        FabricEntityTypeBuilder.create<CubeEntity?>(SpawnGroup.CREATURE)
        { entityType: EntityType<CubeEntity?>?, world: World? -> CubeEntity(entityType, world) }
            .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    )

    fun initialize() {
        FabricDefaultAttributeRegistry.register(CUBE, MobEntity.createMobAttributes())
    }
}