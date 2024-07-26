package com.novvux.petrushka.item.armor

import com.novvux.petrushka.SecretPetrushki
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import java.util.function.Supplier


object ModArmorMaterials {
    const val MODULED_DURABILITY_MULTIPLIER: Int = 15

    val MODULED: RegistryEntry<ArmorMaterial> = registerMaterial("moduled",
        java.util.Map.of<ArmorItem.Type?, Int>(
            ArmorItem.Type.HELMET, 1,
            ArmorItem.Type.CHESTPLATE, 3,
            ArmorItem.Type.LEGGINGS, 2,
            ArmorItem.Type.BOOTS, 1
        ),
        5,
        // The sound played when the armor is equipped.
        SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
        // The ingredient used to repair the armor.
        Supplier<Ingredient> { Ingredient.ofItems(Items.VINE) },
        0.0f,
        0.0f,
        false
    )

    fun registerMaterial(
        id: String?,
        defensePoints: Map<ArmorItem.Type?, Int>?,
        enchantability: Int,
        equipSound: RegistryEntry<SoundEvent?>?,
        repairIngredientSupplier: Supplier<Ingredient>,
        toughness: Float,
        knockbackResistance: Float,
        dyeable: Boolean
    ): RegistryEntry<ArmorMaterial> {
        // Get the supported layers for the armor material
        val layers = listOf<ArmorMaterial.Layer>(ArmorMaterial.Layer(Identifier.of(SecretPetrushki.MOD_ID, id), "", dyeable))

        var material = ArmorMaterial(
            defensePoints,
            enchantability,
            equipSound,
            repairIngredientSupplier,
            layers,
            toughness,
            knockbackResistance
        )
        // Register the material within the ArmorMaterials registry.
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of(SecretPetrushki.MOD_ID, id), material)

        // The majority of the time, you'll want the RegistryEntry of the material - especially for the ArmorItem constructor.
        return RegistryEntry.of(material)
    }
}