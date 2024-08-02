package com.novvux.petrushka.component

import com.novvux.petrushka.api.moduled.ModuledContentsComponent
import net.minecraft.component.ComponentType
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.BundleContentsComponent
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import java.util.function.UnaryOperator

class ModDataComponentTypes {
    companion object {
        val BUNDLE_CONTENTS: ComponentType<ModuledContentsComponent> = register("moduled_contents", UnaryOperator {
            builder: ComponentType.Builder<ModuledContentsComponent> ->
            builder.codec(ModuledContentsComponent.CODEC).packetCodec(ModuledContentsComponent.PACKET_CODEC).cache() })

        private fun <T> register(id: String, builderOperator: UnaryOperator<ComponentType.Builder<T>>): ComponentType<T> {
            return Registry.register(Registries.DATA_COMPONENT_TYPE, id, (builderOperator.apply(ComponentType.builder()) as ComponentType.Builder<T>).build()
            ) as ComponentType<T>
        }
    }
}
