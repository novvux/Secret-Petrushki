package com.novvux.petrushka.item.tool

import net.minecraft.block.Block
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey


class ModuledMaterial : ToolMaterial {
    companion object{ val INSTANCE: ModuledMaterial = ModuledMaterial() }

    override fun getDurability(): Int {
        return 512
    }

    override fun getMiningSpeedMultiplier(): Float {
        return 5.0f
    }

    override fun getAttackDamage(): Float {
        return 1.5f
    }

    override fun getInverseTag(): TagKey<Block> {
        return BlockTags.INCORRECT_FOR_IRON_TOOL
    }

    override fun getEnchantability(): Int {
        return 22
    }

    override fun getRepairIngredient(): Ingredient {
        return Ingredient.ofItems(Items.VINE)
    }
}