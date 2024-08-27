package com.novvux.petrushka

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import java.util.concurrent.CompletableFuture


class SecretPetrushkiDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
		val pack = generator.createPack()

		pack.addProvider { output: FabricDataOutput, completableFuture: CompletableFuture<WrapperLookup> ->
			ModTagGenerator(output, completableFuture)
		}
	}

	private class ModTagGenerator(output: FabricDataOutput, completableFuture: CompletableFuture<WrapperLookup>) :
		FabricTagProvider.ItemTagProvider(output, completableFuture) {

		override fun configure(wrapperLookup: WrapperLookup?) {

		}
	}
}