package com.novvux.petrushka

import com.novvux.petrushka.entity.ModEntitiesClient
import com.novvux.petrushka.particle.ModParticlesClient
import com.novvux.petrushka.render.screen.FleshBoxScreen
import com.novvux.petrushka.render.screen.MutationScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.screen.ingame.HandledScreens
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.screen.ScreenHandler
import net.minecraft.text.Text
import org.lwjgl.glfw.GLFW


@Environment(EnvType.CLIENT)
object SecretPetrushkiClient: ClientModInitializer {
	override fun onInitializeClient() {
		// Initialize client renderers
		ModEntitiesClient.initialize()
		ModParticlesClient.initialize()

		// Initialize keybindins
		val openMenu = KeyBindingHelper.registerKeyBinding(
			KeyBinding(
				"key.petrushka.openmenu",  // Ключ перевода имени привязки ключей
				InputUtil.Type.KEYSYM,  // Тип привязки клавиш, KEYSYM для клавиатуры, MOUSE для мыши.
				GLFW.GLFW_KEY_R,  // Ключевой код ключа
				"category.petrushka.keybinds" // Ключ перевода категории привязки ключей.
			)
		)

		ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: MinecraftClient ->
			while (openMenu.wasPressed()) {
				//val currentScreen = MinecraftClient.getInstance().currentScreen
				//if (currentScreen != null) {
					MinecraftClient.getInstance().setScreen(
						MutationScreen(Text.empty())//, currentScreen)
					)
				//}

				client.player!!.sendMessage(Text.literal("Клавиша 1 была нажата!"), false)
			}
		})

		/*
		HandledScreens.register(
			SecretPetrushki.FLESH_BOX_SCREEN_HANDLER,
			HandledScreens.Provider<ScreenHandler, Screen> { FleshBoxScreen() })
			*/

		// Initialize custom interactions
		//CustomClientInteractions.initialize()
	}
}