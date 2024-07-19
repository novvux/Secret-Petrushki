package com.novvux.petrushka.particles

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.client.particle.*

object ModParticlesClient {
    fun initialize() {
        ParticleFactoryRegistry.getInstance().register(ModParticles.EGOR_PARTICLE, EndRodParticle::Factory)
    }
}