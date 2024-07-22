package com.novvux.petrushka.item.custom

import com.novvux.petrushka.particle.ModParticles
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
    override fun use(world: World, playerEntity: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack> {
        // Spawn particle
        world.addParticle(ModParticles.CIRCLE_PARTICLE, playerEntity.x, playerEntity.y + 0.1, playerEntity.z, 0.0, 0.0, 0.0)

        val frontOfPlayer = playerEntity.blockPos.offset(playerEntity.horizontalFacing, 10)

        /*if (world.isClient) { return TypedActionResult.pass(playerEntity.getStackInHand(hand)) }
        // Spawn the lightning bolt.
        val lightningBolt = LightningEntity(EntityType.LIGHTNING_BOLT, world)
        lightningBolt.setPosition(frontOfPlayer.toCenterPos())
        world.spawnEntity(lightningBolt)*/

        // Return item stack
        return TypedActionResult.success(playerEntity.getStackInHand(hand))
    }

    override fun appendTooltip(stack: ItemStack?, context: TooltipContext?, tooltip: MutableList<Text?>, type: TooltipType?) {
        tooltip.add(Text.translatable("itemTooltip.petrushka.overgrown_diary").formatted(Formatting.GOLD))
    }
}