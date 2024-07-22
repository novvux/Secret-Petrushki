package com.novvux.petrushka.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.client.particle.EndRodParticle

@Environment(EnvType.CLIENT)
object ModParticlesClient {
    fun initialize() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.EGOR_PARTICLE, EndRodParticle::Factory)
        ParticleFactoryRegistry.getInstance().register(ModParticles.CIRCLE_PARTICLE, CircleParticle::Factory)
    }
}