package com.novvux.petrushka

import com.novvux.petrushka.item.ModItems
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper.WrapperLookup
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
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
		val MODIFICATOR: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "modificator"))
		val CRYSTAL: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "crystal"))
		val TIER0CRYSTAL: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "tier0crystal"))
		val TIER1CRYSTAL: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "tier1crystal"))
		val TIER2CRYSTAL: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "tier2crystal"))
		val TIER3CRYSTAL: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "tier3crystal"))

		override fun configure(arg: WrapperLookup) {
			getOrCreateTagBuilder(MODIFICATOR)
				.add(ModItems.DULL_CRYSTAL)
				.add(ModItems.FAINT_CRYSTAL)
				.add(ModItems.GLITTERING_CRYSTAL)
				.add(ModItems.SHINING_CRYSTAL)

				.add(ModItems.SWORD_HEAD)
				.add(ModItems.PICKAXE_HEAD)
				.add(ModItems.AXE_HEAD)
				.add(ModItems.MACE_HEAD)
			// Crystal tag
			getOrCreateTagBuilder(CRYSTAL)
				.add(ModItems.DULL_CRYSTAL)
				.add(ModItems.FAINT_CRYSTAL)
				.add(ModItems.GLITTERING_CRYSTAL)
				.add(ModItems.SHINING_CRYSTAL)
			// Tier 0 crystal
			getOrCreateTagBuilder(TIER0CRYSTAL)
				.add(ModItems.DULL_CRYSTAL)
			// Tier 1 crystal
			getOrCreateTagBuilder(TIER1CRYSTAL)
				.add(ModItems.FAINT_CRYSTAL)
			// Tier 2 crystal
			getOrCreateTagBuilder(TIER2CRYSTAL)
				.add(ModItems.GLITTERING_CRYSTAL)
			// Tier 3 crystal
			getOrCreateTagBuilder(TIER3CRYSTAL)
				.add(ModItems.SHINING_CRYSTAL)
		}
	}
}