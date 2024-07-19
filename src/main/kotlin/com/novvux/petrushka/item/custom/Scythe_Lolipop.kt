package com.novvux.petrushka.item.custom

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting

class Scythe_Lolipop(settings: Settings): Item(settings) {
    override fun appendTooltip(stack: ItemStack?, context: TooltipContext?, tooltip: MutableList<Text?>, type: TooltipType?) {
        tooltip.add(Text.translatable("itemTooltip.petrushka.scythe_lolipop").formatted(Formatting.GOLD))
    }
}