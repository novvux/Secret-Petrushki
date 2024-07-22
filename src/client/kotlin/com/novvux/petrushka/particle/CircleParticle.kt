package com.novvux.petrushka.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType

@Environment(EnvType.CLIENT)
class CircleParticle internal constructor(
    world: ClientWorld,
    x: Double,
    y: Double,
    z: Double,
    spriteProvider: SpriteProvider,
): DirectionalParticle(world, x, y, z, spriteProvider/*, 0.0f*/) {
    init {
        this.scale = 1f
        this.maxAge = 120
        this.setSpriteForAge(spriteProvider)
    }

    override fun move(dx: Double, dy: Double, dz: Double) {
        this.boundingBox = boundingBox.offset(dx, dy, dz)
        this.repositionFromBoundingBox()
    }

    @Environment(EnvType.CLIENT)
    class Factory(private val spriteProvider: SpriteProvider) : ParticleFactory<SimpleParticleType?> {
        override fun createParticle(
            simpleParticleType: SimpleParticleType?,
            clientWorld: ClientWorld,
            d: Double,
            e: Double,
            f: Double,
            g: Double,
            h: Double,
            i: Double
        ): Particle {
            return CircleParticle(clientWorld, d, e, f, this.spriteProvider)
        }
    }
}