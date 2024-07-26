package com.novvux.petrushka.item.custom

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class TooltipItem(private val key: String, private val formatting: Formatting, settings: Settings): Item(settings) {
    override fun appendTooltip(stack: ItemStack?, context: TooltipContext?, tooltip: MutableList<Text?>, type: TooltipType?) {
        tooltip.add(Text.translatable(key).formatted(formatting))
    }
}