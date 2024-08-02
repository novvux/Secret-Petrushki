package com.novvux.petrushka.api.moduled

import net.minecraft.component.type.BundleContentsComponent
import net.minecraft.item.tooltip.TooltipData

@JvmRecord
data class ModuleTooltipData(val content: ModuledContentsComponent): TooltipData {
    fun contents(): ModuledContentsComponent {
        return this.content
    }
}
