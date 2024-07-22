package com.novvux.petrushka.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld

@Environment(EnvType.CLIENT)
open class DirectionalParticle protected constructor(
    world: ClientWorld?,
    x: Double,
    y: Double,
    z: Double,
    spriteProvider: SpriteProvider,
): PreDirectionalParticle(world, x, y, z) {
    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT
    }

    override fun tick() {
        super.tick()
        if (this.age > this.maxAge / 2) {
            this.setAlpha(1.0f - (age.toFloat() - (this.maxAge / 2).toFloat()) / maxAge.toFloat())
        }
    }
}
