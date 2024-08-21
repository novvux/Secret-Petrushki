package com.novvux.petrushka.item.custom

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class Mutator(settings: Settings): Item(settings) {
    override fun use(world: World, playerEntity: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack> {
        // Open UI
        /*
        val currentScreen = MinecraftClient.getInstance().currentScreen
        MinecraftClient.getInstance().setScreen(
            MutationScreen(Text.empty(), currentScreen)
        )
        */

        // Return item stack
        return TypedActionResult.success(playerEntity.getStackInHand(hand))
    }

    /*
    override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
        tooltip.add(Text.translatable("itemTooltip.petrushka.mutator").formatted(Formatting.GOLD))
    }
    */
}