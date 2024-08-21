package com.novvux.petrushka.item

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.component.moduled.ModuledContentsComponent
import com.novvux.petrushka.component.ModComponents
import com.novvux.petrushka.item.armor.ModArmorMaterials
import com.novvux.petrushka.item.custom.Mutator
import com.novvux.petrushka.item.custom.OvergrownDiary
import com.novvux.petrushka.item.custom.TooltipItem
import com.novvux.petrushka.item.custom.moduled.ModuleItem
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.component.type.FoodComponent
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier


object ModItems {
    // Item group name, id and key
    val CUSTOM_ITEM_GROUP_KEY: RegistryKey<ItemGroup> =
        RegistryKey.of(Registries.ITEM_GROUP.key, Identifier.of(SecretPetrushki.MOD_ID, "item_group"))
    val CUSTOM_ITEM_GROUP: ItemGroup = FabricItemGroup.builder()
        .icon { ItemStack(EGOR) }
        .displayName(Text.translatable("itemGroup.petrushka"))
        .build()


    val SUSPICIOUS_FOOD_COMPONENT: FoodComponent = FoodComponent.Builder().alwaysEdible().snack()
        .statusEffect(StatusEffectInstance(StatusEffects.STRENGTH, 6*20, 1), 0.3f).build()
    val SCYTHE_FOOD_COMPONENT: FoodComponent = FoodComponent.Builder().alwaysEdible().snack()
        .statusEffect(StatusEffectInstance(StatusEffects.STRENGTH, 6*20, 1), 0.3f).build()

    // Items
    val OVERGROWN_DIARY: Item = register(OvergrownDiary(Item.Settings().maxCount(1)), "overgrown_diary")
    val MUTATOR: Item = register(Mutator(Item.Settings().maxCount(1)), "mutator")
    val SCYTHE_LOLIPOP: Item = register(TooltipItem("itemTooltip.petrushka.scythe_lollipop", Formatting.GOLD, Item.Settings().food(SCYTHE_FOOD_COMPONENT)), "scythe_lollipop")
    val EGOR: Item = register(Item(Item.Settings().food(SUSPICIOUS_FOOD_COMPONENT)), "egor")

    // Modifiers
    val DULL_CRYSTAL: Item = register(TooltipItem("itemTooltip.petrushka.dull_crystal", Formatting.GOLD, Item.Settings()), "dull_crystal")
    val FAINT_CRYSTAL: Item = register(TooltipItem("itemTooltip.petrushka.faint_crystal", Formatting.GOLD, Item.Settings()), "faint_crystal")
    val GLITTERING_CRYSTAL: Item = register(TooltipItem("itemTooltip.petrushka.glittering_crystal", Formatting.GOLD, Item.Settings()), "glittering_crystal")
    val SHINING_CRYSTAL: Item = register(TooltipItem("itemTooltip.petrushka.shining_crystal", Formatting.GOLD, Item.Settings()), "shining_crystal")

    val SWORD_HEAD: Item = register(TooltipItem("itemTooltip.petrushka.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "sword_head")
    val PICKAXE_HEAD: Item = register(TooltipItem("itemTooltip.petrushka.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "pickaxe_head")
    val AXE_HEAD: Item = register(TooltipItem("itemTooltip.petrushka.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "axe_head")
    val MACE_HEAD: Item = register(TooltipItem("itemTooltip.petrushka.head_modifier", Formatting.GRAY, Item.Settings().maxCount(32)), "mace_head")

    // Tools
    val SQUIGGLE: Item = register(ModuleItem(/*ModuledMaterial.INSTANCE*/ "itemTooltip.petrushka.squiggle",
        Item.Settings().maxCount(1).component(ModComponents.BUNDLE_CONTENTS, ModuledContentsComponent.DEFAULT).component(ModComponents.UPGRADE_SLOTS, 2)), "squiggle")

    // Armor
    val CURLY_HELMET: Item = register(ArmorItem(ModArmorMaterials.MODULED, ArmorItem.Type.HELMET,
        Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ModArmorMaterials.MODULED_DURABILITY_MULTIPLIER))),
        "curly_helmet")
    val CURLY_CHESTPLATE: Item = register(ArmorItem(ModArmorMaterials.MODULED, ArmorItem.Type.CHESTPLATE,
        Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(ModArmorMaterials.MODULED_DURABILITY_MULTIPLIER))),
        "curly_chestplate")
    val CURLY_LEGGINGS: Item = register(ArmorItem(ModArmorMaterials.MODULED, ArmorItem.Type.LEGGINGS,
        Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(ModArmorMaterials.MODULED_DURABILITY_MULTIPLIER))),
        "curly_leggings")
    val CURLY_BOOTS: Item = register(ArmorItem(ModArmorMaterials.MODULED, ArmorItem.Type.BOOTS,
        Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(ModArmorMaterials.MODULED_DURABILITY_MULTIPLIER))),
        "curly_boots")


    fun register(item: Item, id: String?): Item {
        return Registry.register(Registries.ITEM, Identifier.of(SecretPetrushki.MOD_ID, id), item)
    }

    fun initialize() {
        // Register the item group
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP)

        // Register items to the custom item group
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries ->
                itemGroup.add(SCYTHE_LOLIPOP)
                itemGroup.add(OVERGROWN_DIARY)
                itemGroup.add(MUTATOR)
                itemGroup.add(EGOR)

                itemGroup.add(DULL_CRYSTAL)
                itemGroup.add(FAINT_CRYSTAL)
                itemGroup.add(GLITTERING_CRYSTAL)
                itemGroup.add(SHINING_CRYSTAL)

                itemGroup.add(SWORD_HEAD)
                itemGroup.add(PICKAXE_HEAD)
                itemGroup.add(AXE_HEAD)
                itemGroup.add(MACE_HEAD)

                itemGroup.add(SQUIGGLE)

                itemGroup.add(CURLY_HELMET)
                itemGroup.add(CURLY_CHESTPLATE)
                itemGroup.add(CURLY_LEGGINGS)
                itemGroup.add(CURLY_BOOTS)
            })
    }
}
