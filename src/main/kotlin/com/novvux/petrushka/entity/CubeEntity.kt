package com.novvux.petrushka.entity

import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.world.World


class CubeEntity(entityType: EntityType<out PathAwareEntity?>?, world: World?) : PathAwareEntity(entityType, world)