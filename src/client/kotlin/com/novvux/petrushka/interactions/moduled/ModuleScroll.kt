package com.novvux.petrushka.interactions.moduled

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.item.custom.moduled.ModuleItem.Companion.shiftBundle
import com.novvux.petrushka.mixin.client.IHandledScreenInvoker
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.server.network.ServerPlayerEntity


object ModuleScroll {
    private var accScroll = 0.0

    fun onMouseScrolled(screen: HandledScreen<*>, x: Double, y: Double, scroll: Double): Boolean {
        val slot = (screen as IHandledScreenInvoker).invokeGetSlotAt(x, y) ?: return true
        val stack = slot.stack
        if (accScroll * scroll < 0) accScroll = 0.0
        accScroll += scroll
        val amt = accScroll.toInt()
        accScroll -= amt.toDouble()
        if (amt == 0) {
            return true
        }

        shiftBundle(stack, amt)

        val buf = PacketByteBufs.create()

        //buf.writeInt(screen.screenHandler.syncId)
        //buf.writeInt(screen.screenHandler.revision)
        buf.writeInt(slot.id)
        buf.writeInt(amt)

        //ClientPlayNetworking.send(SecretPetrushki.SCROLL_PACKET_ID, buf)

        return false
    }
}