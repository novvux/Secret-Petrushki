package com.novvux.petrushka.item.custom

import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World


class Overgorwn_Diary(settings: Settings): Item(settings) {
    override fun use(world: World, user: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack> {
        if (world.isClient) {
            return TypedActionResult.pass(user.getStackInHand(hand))
        }

        val frontOfPlayer = user.blockPos.offset(user.horizontalFacing, 10)

        // Spawn the lightning bolt.
        val lightningBolt = LightningEntity(EntityType.LIGHTNING_BOLT, world)
        lightningBolt.setPosition(frontOfPlayer.toCenterPos())
        world.spawnEntity(lightningBolt)

        // Nothing has changed to the item stack,
        // so we just return it how it was.
        return TypedActionResult.success(user.getStackInHand(hand))
    }

    override fun appendTooltip(stack: ItemStack?, context: TooltipContext?, tooltip: MutableList<Text?>, type: TooltipType?) {
        tooltip.add(Text.translatable("itemTooltip.petrushka.overgrown_diary").formatted(Formatting.GOLD))
    }
}