package com.novvux.petrushka.entity

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.block.ModBlocks
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.*
import net.minecraft.entity.mob.MobEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


object ModEntities {
    @Suppress("DEPRECATION")
    val CUBE: EntityType<CubeEntity> = Registry.register(
        Registries.ENTITY_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "cube"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE)
        { entityType: EntityType<CubeEntity>, world: World -> CubeEntity(entityType, world) }
            .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    )

    val FLESH_BOX_BLOCK_ENTITY: BlockEntityType<FleshBoxBlockEntity> =
        Registry.register<BlockEntityType<*>, BlockEntityType<FleshBoxBlockEntity>>(
            Registries.BLOCK_ENTITY_TYPE,
            Identifier.of(SecretPetrushki.MOD_ID, "flesh_box_entity"),
            BlockEntityType.Builder.create({ pos: BlockPos, state: BlockState -> FleshBoxBlockEntity(pos, state)
            }, ModBlocks.FLESH_BOX_BLOCK).build()
        )

    /*
    val FLESH_BOX_BLOCK_ENTITY: BlockEntityType<*>? = Registry.register(
        Registries.BLOCK_ENTITY_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "flesh_box"),
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE)
        { entityType: EntityType<FleshBoxBlockEntity>, world: World -> FleshBoxBlockEntity(entityType, world) }
            .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    )
    */

    fun initialize() {
        FabricDefaultAttributeRegistry.register(CUBE, MobEntity.createMobAttributes())
    }
}