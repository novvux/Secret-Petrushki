package com.novvux.petrushka.block.custom

import com.mojang.serialization.MapCodec
import com.novvux.petrushka.entity.FleshBoxBlockEntity
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.screen.ScreenHandler
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World


/*
class FleshBoxBlock(settings: Settings): Block(settings), BlockEntityProvider {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return FleshBoxBlockEntity(pos, state)
    }

    override fun onUse(blockState: BlockState?, world: World?, blockPos: BlockPos?, player: PlayerEntity?, blockHitResult: BlockHitResult?): ActionResult {
        if (world!!.isClient) return ActionResult.SUCCESS
        val blockEntity: Inventory = world.getBlockEntity(blockPos) as Inventory
        val hand: Hand = player?.preferredHand ?: return ActionResult.SUCCESS

        if (!player.getStackInHand(hand).isEmpty) {
            // Check what is the first open slot and put an item from the player's hand there
            when {
                blockEntity.getStack(0).isEmpty -> {
                    // Put the stack the player is holding into the inventory
                    blockEntity.setStack(0, player.getStackInHand(hand).copy())
                    // Remove the stack from the player's hand
                    player.getStackInHand(hand).count = 0
                }
                blockEntity.getStack(1).isEmpty -> {
                    blockEntity.setStack(1, player.getStackInHand(hand).copy())
                    player.getStackInHand(hand).count = 0
                }
                else -> {
                    // If the inventory is full we'll print its contents
                    println("The first slot holds ${blockEntity.getStack(0)} and the second slot holds ${blockEntity.getStack(1)}")
                }
            }
        } else {
            // If the player is not holding anything we'll get give him the items in the block entity one by one

            // Find the first slot that has an item and give it to the player
            if (!blockEntity.getStack(1).isEmpty) {
                // Give the player the stack in the inventory
                player.inventory.offerOrDrop(blockEntity.getStack(1))
                // Remove the stack from the inventory
                blockEntity.removeStack(1)
            } else if (!blockEntity.getStack(0).isEmpty) {
                player.inventory.offerOrDrop(blockEntity.getStack(0))
                blockEntity.removeStack(0)
            }
        }

        return ActionResult.SUCCESS
    }

    //This method will drop all items onto the ground when the block is broken
    public override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
        if (state.block !== newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is FleshBoxBlockEntity) {
                ItemScatterer.spawn(world, pos, DefaultedList.of())//blockEntity as FleshBoxBlockEntity?)
                // update comparators
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    public override fun hasComparatorOutput(state: BlockState): Boolean {
        return true
    }

    public override fun getComparatorOutput(state: BlockState, world: World, pos: BlockPos): Int {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos))
    }

    public override fun getRenderType(state: BlockState?): BlockRenderType {
        // With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL
    }

    /*
    override fun <T : BlockEntity?> getTicker(world: World, state: BlockState, type: BlockEntityType<T>): BlockEntityTicker<T> {
        return checkType(type, ModEntities.FLESH_BOX_BLOCK_ENTITY) { world1, pos, state1, be ->
            FleshBoxBlockEntity.tick(
                world1,
                pos,
                state1,
                be
            )
        }
    }
    */
}
*/


open class FleshBoxBlock(settings: Settings?) : BlockWithEntity(settings) {
    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return FleshBoxBlockEntity(pos, state)
    }

    public override fun getRenderType(state: BlockState): BlockRenderType {
        //With inheriting from BlockWithEntity this defaults to INVISIBLE, so we need to change that!
        return BlockRenderType.MODEL
    }

    override fun onUse(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, blockHitResult: BlockHitResult): ActionResult {
        if (!world.isClient) {
            println("block najat")
            //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
            //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
            val screenHandlerFactory = state.createScreenHandlerFactory(world, pos)

            if (screenHandlerFactory != null) {
                //With this call the server will request the client to open the appropriate Screenhandler
                player.openHandledScreen(screenHandlerFactory)
            }
        }
        return ActionResult.SUCCESS
    }

    override fun getCodec(): MapCodec<out BlockWithEntity> {
        TODO("Not yet implemented")
    }

    //This method will drop all items onto the ground when the block is broken
    public override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
        if (state.block !== newState.block) {
            val blockEntity = world.getBlockEntity(pos)
            if (blockEntity is FleshBoxBlockEntity) {
                ItemScatterer.spawn(world, pos, blockEntity as FleshBoxBlockEntity?)
                // update comparators
                world.updateComparators(pos, this)
            }
            super.onStateReplaced(state, world, pos, newState, moved)
        }
    }

    public override fun hasComparatorOutput(state: BlockState): Boolean {
        return true
    }

    public override fun getComparatorOutput(state: BlockState, world: World, pos: BlockPos): Int {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos))
    }
}

