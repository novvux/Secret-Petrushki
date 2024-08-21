package com.novvux.petrushka.handler

import com.novvux.petrushka.SecretPetrushki
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.Inventory
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.slot.Slot


class FleshBoxScreenHandler @JvmOverloads constructor(syncId: Int, playerInventory: PlayerInventory, inventory: Inventory = SimpleInventory(9)):
    ScreenHandler(SecretPetrushki.FLESH_BOX_SCREEN_HANDLER, syncId) {
    private val inventory: Inventory

    //This constructor gets called from the BlockEntity on the server without calling the other constructor first, the server knows the inventory of the container
    //and can therefore directly provide it as an argument. This inventory will then be synced to the client.
    //This constructor gets called on the client when the server wants it to open the screenHandler,
    //The client will call the other constructor with an empty Inventory and the screenHandler will automatically
    //sync this empty inventory with the inventory on the server.
    init {
        checkSize(inventory, 9)
        this.inventory = inventory
        //some inventories do custom logic when a player opens it.
        inventory.onOpen(playerInventory.player)
        var l: Int
        //Our inventory


        //This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
        //This will not render the background of the slots however, this is the Screens job
        var m = 0
        while (m < 3) {
            l = 0
            while (l < 3) {
                this.addSlot(Slot(inventory, l + m * 3, 62 + l * 18, 17 + m * 18))
                ++l
            }
            ++m
        }
        //The player inventory
        m = 0
        while (m < 3) {
            l = 0
            while (l < 9) {
                this.addSlot(Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18))
                ++l
            }
            ++m
        }
        //The player Hotbar
        m = 0
        while (m < 9) {
            this.addSlot(Slot(playerInventory, m, 8 + m * 18, 142))
            ++m
        }
    }

    override fun canUse(player: PlayerEntity): Boolean {
        return inventory.canPlayerUse(player)
    }

    // Shift + Player Inv Slot
    override fun quickMove(player: PlayerEntity, invSlot: Int): ItemStack {
        var newStack = ItemStack.EMPTY
        val slot = slots[invSlot]
        if (slot.hasStack()) {
            val originalStack = slot.stack
            newStack = originalStack.copy()
            if (invSlot < inventory.size()) if (!this.insertItem(originalStack, inventory.size(), slots.size, true)) return ItemStack.EMPTY
            else if (!this.insertItem(originalStack, 0, inventory.size(), false)) return ItemStack.EMPTY

            if (originalStack.isEmpty) slot.stack = ItemStack.EMPTY
            else slot.markDirty()
        }

        return newStack
    }
}

