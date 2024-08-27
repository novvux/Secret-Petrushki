package com.novvux.petrushka

import com.novvux.petrushka.entity.ModEntitiesClient
import com.novvux.petrushka.particle.ModParticlesClient
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW


@Environment(EnvType.CLIENT)
class SecretPetrushkiClient: ClientModInitializer {
	override fun onInitializeClient() {
		// Initialize client renderers
		ModEntitiesClient.initialize()
		ModParticlesClient.initialize()

		//HandledScreens.register(SecretPetrushki.FLESH_BOX_SCREEN_HANDLER, HandledScreens.Provider<ScreenHandler, Screen> { FleshBoxScreen() })
	}
}