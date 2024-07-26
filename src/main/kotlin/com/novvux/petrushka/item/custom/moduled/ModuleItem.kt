package com.novvux.petrushka.item.custom.moduled

import com.novvux.petrushka.component.ModComponents
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BundleItem
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World


class ModuleItem(private val translationKey: String, settings: Settings): BundleItem(settings) {
    private var SLOTS: Byte = 64

    override fun use(world: World, user: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack> {
        return TypedActionResult.success(user.getStackInHand(hand))
    }

    override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
        val bundleContentsComponent = stack.get(DataComponentTypes.BUNDLE_CONTENTS)
        val stack: Int = stack[ModComponents.UPGRADE_SLOTS]!!
        if (bundleContentsComponent != null) {
            val i = MathHelper.multiplyFraction(bundleContentsComponent.occupancy, 64)
            tooltip.add(Text.translatable(translationKey).formatted(Formatting.GOLD))
            tooltip.add(Text.translatable("item.minecraft.bundle.fullness", *arrayOf<Any>(i, SLOTS)).formatted(Formatting.GRAY))
            tooltip.add(Text.translatable("abeba: ", stack).formatted(Formatting.GOLD))
        }
    }
}
