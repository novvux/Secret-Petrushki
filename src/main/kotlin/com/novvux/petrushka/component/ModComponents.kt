package com.novvux.petrushka.component

import com.mojang.serialization.Codec
import com.novvux.petrushka.SecretPetrushki
import net.minecraft.component.ComponentType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


object ModComponents {
    @JvmField
    val UPGRADE_SLOTS: ComponentType<Byte> = Registry.register<ComponentType<*>, ComponentType<Byte>>(
        Registries.DATA_COMPONENT_TYPE,
        Identifier.of(SecretPetrushki.MOD_ID, "upgrade_slots"),
        ComponentType.builder<Byte>().codec(Codec.BYTE).build()
    )

    /*val MODULE_DATA_COMPONENT: ComponentType<ModuleDataComponent> = Registry.register(
        Registries.DATA_COMPONENT_TYPE,
        Identifier.of(SecretPetrushki.MOD_ID, "module_data_component"),
        ComponentType.builder<ModuleDataComponent>().codec(ModuleDataComponent.CODEC).build()
    )*/
}