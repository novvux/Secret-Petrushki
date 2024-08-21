package com.novvux.petrushka.component

import com.mojang.serialization.Codec
import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.component.moduled.ModuledContentsComponent
import net.minecraft.component.ComponentType
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.BundleContentsComponent
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import java.util.function.UnaryOperator


object ModComponents {
    @JvmField
    val UPGRADE_SLOTS: ComponentType<Byte> = Registry.register<ComponentType<*>, ComponentType<Byte>>(
        Registries.DATA_COMPONENT_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "upgrade_slots"),
        ComponentType.builder<Byte>().codec(Codec.BYTE).build()
    )

    /*
    val MODULED_CONTENTS: ComponentType<Byte> = Registry.register<ComponentType<*>, ComponentType<Byte>>(
        Registries.DATA_COMPONENT_TYPE,
        Identifier.of(SecretPetrushki.MOD_ID, "moduled_contents"),
        ComponentType.builder<Byte>().codec(Codec.BYTE).build()
    )
    */

    val BUNDLE_CONTENTS: ComponentType<ModuledContentsComponent> =
        Registry.register<ComponentType<*>, ComponentType<ModuledContentsComponent>>(
            Registries.DATA_COMPONENT_TYPE, Identifier.of(SecretPetrushki.MOD_ID, "moduled_contents"),
            ComponentType.builder<ModuledContentsComponent>().codec(ModuledContentsComponent.CODEC).packetCodec(ModuledContentsComponent.PACKET_CODEC).build()
        )

    /*
    val BUNDLE_CONTENTS: ComponentType<ModuledContentsComponent> = register("moduled_contents") {
        builder: ComponentType.Builder<ModuledContentsComponent> ->
        builder.codec(ModuledContentsComponent.CODEC).packetCodec(ModuledContentsComponent.PACKET_CODEC).cache()
    }

    private fun register(id: String, builderOperator: UnaryOperator<ComponentType.Builder<Any>>): ComponentType<ModuledContentsComponent> {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, "moduled_contents", builderOperator.apply(ComponentType.builder()).build())
    }
    */
}