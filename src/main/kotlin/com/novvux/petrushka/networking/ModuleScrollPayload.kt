package com.novvux.petrushka.networking

import com.novvux.petrushka.SecretPetrushki
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.network.packet.CustomPayload
import net.minecraft.util.math.BlockPos

@JvmRecord
data class ModuleScrollPayload(val slot: Int, val accScroll: Int): CustomPayload {
    override fun getId(): CustomPayload.Id<out CustomPayload?> {
        return ID
    }

    companion object {
        val ID: CustomPayload.Id<ModuleScrollPayload> = CustomPayload.Id<ModuleScrollPayload>(SecretPetrushki.SCROLL_PACKET_ID)
        val CODEC: PacketCodec<RegistryByteBuf, ModuleScrollPayload> = PacketCodec.tuple(
            PacketCodecs.INTEGER, ModuleScrollPayload::slot,
            PacketCodecs.INTEGER, ModuleScrollPayload::accScroll
        ) {
            slot: Int, accScroll: Int -> ModuleScrollPayload(slot, accScroll)
        }
    }
}