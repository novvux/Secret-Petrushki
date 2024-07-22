package com.novvux.petrushka.particle

import com.novvux.petrushka.SecretPetrushki
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.SimpleParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


object ModParticles {
    val EGOR_PARTICLE: SimpleParticleType = registerParticle("egor_particle", FabricParticleTypes.simple())
    val CIRCLE_PARTICLE: SimpleParticleType = registerParticle("circle_particle", FabricParticleTypes.simple())

    private fun registerParticle(name: String, particleType: SimpleParticleType): SimpleParticleType {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SecretPetrushki.MOD_ID, name), particleType)
    }

    /*fun initialize() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "egor_particle"), EGOR_PARTICLE)
    }*/
}