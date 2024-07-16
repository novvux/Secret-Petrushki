package com.novvux.petrushka.block

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.item.ModItems
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier


object ModBlocks {
    val STRANGE_MELON: Block = register(
        Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.SHROOMLIGHT).strength(4.0f).requiresTool()),
        "strange_melon", true)

    fun register(block: Block, name: String, shouldRegisterItem: Boolean): Block {
        // Register the block and its item
        val id: Identifier = Identifier.of(SecretPetrushki.MOD_ID, name)

        // Should block have item?
        if (shouldRegisterItem) {
            val blockItem = BlockItem(block, Item.Settings())
            Registry.register(Registries.ITEM, id, blockItem)
        }

        return Registry.register(Registries.BLOCK, id, block)
    }

    fun initialize() {
        // Add block items to group
        ItemGroupEvents.modifyEntriesEvent(ModItems.CUSTOM_ITEM_GROUP_KEY)
            .register(ModifyEntries { itemGroup: FabricItemGroupEntries ->
                itemGroup.add(STRANGE_MELON.asItem())
            })
    };
}