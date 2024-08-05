package com.novvux.petrushka.interactions

import com.novvux.petrushka.interactions.moduled.ModuleScroll.onMouseScrolled
import com.novvux.petrushka.networking.ModuleScrollPayload
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.network.packet.CustomPayload


class CustomClientInteractions {
    companion object {
        fun initialize() {
            ScreenEvents.BEFORE_INIT.register { client, screen, scaledWidth, scaledHeight ->
                if (screen is HandledScreen<*>) {
                    ScreenMouseEvents.afterMouseScroll(screen).register { _screen, x, y, horiz, vert ->
                        onMouseScrolled(screen, x, y, vert)
                    }
                }
            }

            /*ClientPlayNetworking.registerGlobalReceiver<CustomPayload>(
                ModuleScrollPayload.ID
            ) { payload: CustomPayload, context: ClientPlayNetworking.Context ->
                context.client().execute {
                    ModuleScrollPayload.highlightBlock(client, payload.blockPos())
                }
            }*/
        }
    }
}