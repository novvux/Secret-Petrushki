package com.novvux.petrushka.entity

import com.novvux.petrushka.handler.FleshBoxScreenHandler
import com.novvux.petrushka.inventory.ImplementedInventory
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventories
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.listener.ClientPlayPacketListener
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.stream.Stream


/*
class FleshBoxBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModEntities.FLESH_BOX_BLOCK_ENTITY, pos, state), NamedScreenHandlerFactory, ImplementedInventory {
    //From the ImplementedInventory Interface
    override val items: DefaultedList<ItemStack?> = DefaultedList.ofSize(9, ItemStack.EMPTY)
    override fun markDirty() {
        TODO("Not yet implemented")
    }

    //These Methods are from the NamedScreenHandlerFactory Interface
    //createMenu creates the ScreenHandler itself
    //getDisplayName will Provide its name which is normally shown at the top
    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler

        return FleshBoxScreenHandler(syncId, playerInventory, this)
    }

    override fun getDisplayName(): Text {
        return Text.translatable(cachedState.block.translationKey)
    }

    /*
    fun readNbt(nbt: NbtCompound) {
        super.readNbt(nbt)
        Inventories.readNbt(nbt, this.items)
    }

    fun writeNbt(nbt: NbtCompound): NbtCompound {
        super.writeNbt(nbt)
        Inventories.writeNbt(nbt, this.items)
        return nbt
    }
    */
}
*/

class FleshBoxBlockEntity(pos: BlockPos, state: BlockState): BlockEntity(ModEntities.FLESH_BOX_BLOCK_ENTITY, pos, state), NamedScreenHandlerFactory, ImplementedInventory {
    override val items: DefaultedList<ItemStack> = DefaultedList.ofSize(9, ItemStack.EMPTY)
    override fun markDirty() {

    }

    //These Methods are from the NamedScreenHandlerFactory Interface
    //createMenu creates the ScreenHandler itself
    //getDisplayName will Provide its name which is normally shown at the top
    override fun createMenu(syncId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ScreenHandler {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return FleshBoxScreenHandler(syncId, playerInventory, this)
    }

    override fun getDisplayName(): Text {
        return Text.translatable(cachedState.block.translationKey)
    }

    override fun readNbt(nbt: NbtCompound, wrapper: WrapperLookup) {
        super.readNbt(nbt, wrapper)
        Inventories.readNbt(nbt, items, wrapper)
    }

    override fun writeNbt(nbt: NbtCompound, wrapper: WrapperLookup) {
        Inventories.writeNbt(nbt, items, wrapper)
        return super.writeNbt(nbt, wrapper)
    }

    override fun toUpdatePacket(): Packet<ClientPlayPacketListener>? {
        return BlockEntityUpdateS2CPacket.create(this)
    }


    fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt(WrapperLookup.of(Stream.empty()))
    }

    fun tick(world: World, pos: BlockPos, state: BlockState, be: FleshBoxBlockEntity) {

    }
}

