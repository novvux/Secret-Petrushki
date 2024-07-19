package com.novvux.petrushka.particles

import com.novvux.petrushka.SecretPetrushki
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.SimpleParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


object ModParticles {
    val EGOR_PARTICLE: SimpleParticleType = FabricParticleTypes.simple()
    //val GREEN_FLAME: SimpleParticleType = registerParticle("green_flame", FabricParticleTypes.simple())

    /*private fun registerParticle(name: String, particleType: SimpleParticleType): SimpleParticleType {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SecretPetrushki.MOD_ID, name), particleType)
    }*/

    fun initialize() {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "egor_particle"), EGOR_PARTICLE)
    }
}