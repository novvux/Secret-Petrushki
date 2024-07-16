package com.novvux.petrushka.item

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
import net.minecraft.util.Identifier

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.item.custom.Overgorwn_Diary


object ModItems {
    // Item Group
    val CUSTOM_ITEM_GROUP_KEY: RegistryKey<ItemGroup> =
        RegistryKey.of(Registries.ITEM_GROUP.key, Identifier.of(SecretPetrushki.MOD_ID, "item_group"))
    val CUSTOM_ITEM_GROUP: ItemGroup = FabricItemGroup.builder()
        .icon { ItemStack(EGOR) }
        .displayName(Text.translatable("itemGroup.petrushka"))
        .build()


    val SUSPICIOUS_FOOD_COMPONENT: FoodComponent = FoodComponent.Builder().alwaysEdible().snack()
        .statusEffect(StatusEffectInstance(StatusEffects.POISON, 6*20, 1), 0.3f).build()

    val EGOR: Item = register(Item(Item.Settings().food(SUSPICIOUS_FOOD_COMPONENT)), "egor")
    val OVERGROWN_DIARY: Item = register(Overgorwn_Diary(Item.Settings().maxCount(1)), "overgrown_diary")

    fun register(item: Item, id: String?): Item {
        return Registry.register(Registries.ITEM, Identifier.of(SecretPetrushki.MOD_ID, id), item)
    }

    fun initialize() {
        // Register the group.
        Registry.register(Registries.ITEM_GROUP, CUSTOM_ITEM_GROUP_KEY, CUSTOM_ITEM_GROUP)

        // Register items to the custom item group.
        ItemGroupEvents.modifyEntriesEvent(CUSTOM_ITEM_GROUP_KEY)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries ->
                itemGroup.add(OVERGROWN_DIARY)
                itemGroup.add(EGOR)
            })
    }
}
