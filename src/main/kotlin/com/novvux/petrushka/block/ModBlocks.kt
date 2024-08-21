package com.novvux.petrushka.block

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.block.custom.FleshBoxBlock
import com.novvux.petrushka.entity.FleshBoxBlockEntity
import com.novvux.petrushka.item.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos


object ModBlocks {
    val STRANGE_MELON: Block = register(
        Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.SHROOMLIGHT).strength(1.0f)), "strange_melon", true)
    //val FLESH_BOX: Block = register(
    //    Block(AbstractBlock.Settings.create().sounds(BlockSoundGroup.SHROOMLIGHT).strength(1.0f)), "flesh_box", true)

    val FLESH_BOX_BLOCK: Block
    private val FLESH_BOX_BLOCK_ITEM: BlockItem
    private var FLESH_BOX_BLOCK_ENTITY: BlockEntityType<FleshBoxBlockEntity>

    // a public identifier for multiple parts of our bigger chest
    private val FLESH_BOX: Identifier = Identifier.of(SecretPetrushki.MOD_ID, "flesh_box")

    init {
        FLESH_BOX_BLOCK = Registry.register<Block, Block>(
            Registries.BLOCK, FLESH_BOX, FleshBoxBlock(FabricBlockSettings.copyOf(Blocks.CHEST))
        )
            FLESH_BOX_BLOCK_ITEM = Registry.register(Registries.ITEM, FLESH_BOX, BlockItem(FLESH_BOX_BLOCK, Item.Settings()))

        //The parameter of build at the very end is always null, do not worry about it
        FLESH_BOX_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE, FLESH_BOX,
            FabricBlockEntityTypeBuilder.create({ pos: BlockPos, state: BlockState -> FleshBoxBlockEntity(pos, state) }, FLESH_BOX_BLOCK).build(null))
    }

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
                //itemGroup.add(FLESH_BOX.asItem())
            })
    }
}