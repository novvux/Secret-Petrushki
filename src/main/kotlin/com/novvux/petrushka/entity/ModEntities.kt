package com.novvux.petrushka.entity

import com.novvux.petrushka.SecretPetrushki
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.entity.*
import net.minecraft.entity.SpawnRestriction.SpawnPredicate
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.WardenEntity.addAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World


object ModEntities {/*


    fun register(entity: Entity, name: String, group: String): Block {
        // Register the block and its item
        val id: Identifier = Identifier.of(SecretPetrushki.MOD_ID, name)

        val CUBE: EntityType<CubeEntity> = Registry.register(
            Registries.ENTITY_TYPE, Identifier(SecretPetrushki.MOD_ID, "cube"),
            FabricEntityTypeBuilder.create<CubeEntity?>(SpawnGroup.CREATURE)
            { entityType: EntityType<CubeEntity?>?, world: World? -> CubeEntity(entityType, world) }
                .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
        )

        return Registry.register(Registries.BLOCK, id, block)
    }

    fun initialize() {

    }
}
object EntityRegistry : BaseEntityRegistry() {
    val CUBE: EntityType<CubeEntity> =
        Registry.register(Registries.ENTITY_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "cube"),
        FabricEntityTypeBuilder.create<CubeEntity?>(SpawnGroup.CREATURE)
        { entityType: EntityType<CubeEntity?>?, world: World? -> CubeEntity(entityType, world) }
            .dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    )

    fun initRestrictions() {

    }

    fun initSpawns() {

    }

    fun initialize() {
        addAttributes(CUBE, MobEntity.createMobAttributes())

        initRestrictions()
        initSpawns()
    }*/
}