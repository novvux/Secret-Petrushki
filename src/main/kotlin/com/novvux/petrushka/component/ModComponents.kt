package com.novvux.petrushka.component

import com.mojang.serialization.Codec
import com.novvux.petrushka.SecretPetrushki
import net.minecraft.component.ComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


object ModComponents {
    val UPGRADE_SLOTS: ComponentType<Int> = Registry.register<ComponentType<*>, ComponentType<Int>>(
        Registries.DATA_COMPONENT_TYPE,
        Identifier.of(SecretPetrushki.MOD_ID, "click_count"),
        ComponentType.builder<Int>().codec(Codec.INT).build()
    )
}