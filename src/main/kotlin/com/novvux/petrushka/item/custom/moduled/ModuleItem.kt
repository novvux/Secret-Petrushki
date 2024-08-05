package com.novvux.petrushka.item.custom.moduled

import com.novvux.petrushka.SecretPetrushki
import com.novvux.petrushka.api.moduled.ModuledContentsComponent
import com.novvux.petrushka.component.ModComponents
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil.isIn
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.BundleContentsComponent
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.StackReference
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsage
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.screen.slot.Slot
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.*
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import org.apache.commons.lang3.math.Fraction
import java.util.function.Consumer
import kotlin.math.min


open class ModuleItem(private val translationKey: String, settings: Settings) : Item(settings) {
    override fun onStackClicked(stack: ItemStack, slot: Slot, clickType: ClickType, player: PlayerEntity): Boolean {
        if (clickType != ClickType.RIGHT) return false
        else {
            val bundleContentsComponent = stack.get(ModComponents.BUNDLE_CONTENTS)
            val slots: Byte? = stack.get(ModComponents.UPGRADE_SLOTS)
            if (bundleContentsComponent == null) return false
            else {
                val itemStack = slot.stack
                val builder = ModuledContentsComponent.Builder(bundleContentsComponent)
                if (itemStack.isEmpty) {
                    this.playRemoveOneSound(player)
                    val itemStack2 = builder.removeFirst()
                    if (itemStack2 != null) {
                        val itemStack3 = slot.insertStack(itemStack2)
                        if (slots != null) builder.add(itemStack3, slots) else builder.add(itemStack3, 2)
                    }
                } else if (itemStack.item.canBeNested()) {
                    val i = if (slots != null) builder.add(slot, player, slots) else builder.add(slot, player, 2)
                    if (i > 0) this.playInsertSound(player)
                }

                stack.set(ModComponents.BUNDLE_CONTENTS, builder.build())
                return true
            }
        }
    }

    override fun onClicked(stack: ItemStack, otherStack: ItemStack, slot: Slot, clickType: ClickType, player: PlayerEntity, cursorStackReference: StackReference): Boolean {
        if (clickType == ClickType.RIGHT && slot.canTakePartial(player)) {
            val moduledContentsComponent = stack.get(ModComponents.BUNDLE_CONTENTS)
            if (moduledContentsComponent == null) return false
            else {
                val builder = ModuledContentsComponent.Builder(moduledContentsComponent)
                if (otherStack.isEmpty) {
                    val itemStack = builder.removeFirst()
                    if (itemStack != null) {
                        this.playRemoveOneSound(player)
                        cursorStackReference.set(itemStack)
                        if (isIn(TagKey(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "crystal")), itemStack.item)){
                            dropAllBundledItems(stack, player)
                            playDropContentsSound(player)
                        }
                    }
                } else {
                    var slots: Byte? = stack.get(ModComponents.UPGRADE_SLOTS)
                    if (slots === null) slots = 3
                    val i = builder.add(otherStack, slots)
                    if (i > 0) this.playInsertSound(player)
                }

                stack.set(ModComponents.BUNDLE_CONTENTS, builder.build())
                return true
            }
        } else return false
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = user.getStackInHand(hand)
        //var slots = stack.get(ModComponents.UPGRADE_SLOTS)
        //slots = if (slots != null) (slots + 1).toByte() else 3

        shiftBundle(stack, 1)

        return TypedActionResult.success(user.getStackInHand(hand))
    }

    override fun isItemBarVisible(stack: ItemStack): Boolean {
        val bundleContentsComponent = stack.getOrDefault(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT) as BundleContentsComponent
        return bundleContentsComponent.occupancy > Fraction.ZERO
    }

    override fun getItemBarStep(stack: ItemStack): Int {
        val bundleContentsComponent = stack.getOrDefault(ModComponents.BUNDLE_CONTENTS, ModuledContentsComponent.DEFAULT) as ModuledContentsComponent
        return min((1 + MathHelper.multiplyFraction(bundleContentsComponent.occupancy, 12)).toDouble(), 13.0).toInt()
    }

    override fun getItemBarColor(stack: ItemStack): Int {
        return ITEM_BAR_COLOR
    }

    /*override fun getTooltipData(stack: ItemStack): Optional<TooltipData> {
        return if (!stack.contains(DataComponentTypes.HIDE_TOOLTIP) && !stack.contains(DataComponentTypes.HIDE_ADDITIONAL_TOOLTIP)) Optional.ofNullable(
            stack.get(ModComponents.BUNDLE_CONTENTS)
        ).map { bundleContentsComponent: ModuledContentsComponent -> ModuleTooltipData(bundleContentsComponent)
        } else Optional.empty()
    }*/

    override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
        val moduledContentsComponent = stack.get(ModComponents.BUNDLE_CONTENTS)
        val slots: Byte? = stack.get(ModComponents.UPGRADE_SLOTS)
        tooltip.add(Text.translatable(translationKey).formatted(Formatting.GOLD))
        if (moduledContentsComponent != null && slots != null) {
            tooltip.add(Text.of(moduledContentsComponent.stacks.toString()))

            val i = MathHelper.multiplyFraction(moduledContentsComponent.occupancy, slots.toInt())
            tooltip.add(Text.translatable("item.minecraft.bundle.fullness", *arrayOf<Any>(i, slots)).formatted(Formatting.GRAY))
        }
    }

    override fun onItemEntityDestroyed(entity: ItemEntity) {
        val bundleContentsComponent = entity.stack.get(ModComponents.BUNDLE_CONTENTS)
        if (bundleContentsComponent != null) {
            entity.stack.set(ModComponents.BUNDLE_CONTENTS, ModuledContentsComponent.DEFAULT)
            ItemUsage.spawnItemContents(entity, ModuledContentsComponent.iterateCopy())
        }
    }

    private fun playRemoveOneSound(entity: Entity) { entity.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 0.8f, 0.8f + entity.world.getRandom().nextFloat() * 0.4f) }
    private fun playInsertSound(entity: Entity) { entity.playSound(SoundEvents.ITEM_BUNDLE_INSERT, 0.8f, 0.8f + entity.world.getRandom().nextFloat() * 0.4f) }
    private fun playDropContentsSound(entity: Entity) { entity.playSound(SoundEvents.ITEM_BUNDLE_DROP_CONTENTS, 0.8f, 0.8f + entity.world.getRandom().nextFloat() * 0.4f) }

    companion object {
        private val ITEM_BAR_COLOR = MathHelper.packRgb(0.4f, 0.4f, 1.0f)

        private fun dropAllBundledItems(stack: ItemStack, player: PlayerEntity): Boolean {
            val moduledContentsComponent = stack.get(ModComponents.BUNDLE_CONTENTS)
            if (moduledContentsComponent != null && !ModuledContentsComponent.isEmpty) {
                stack.set(ModComponents.BUNDLE_CONTENTS, ModuledContentsComponent.DEFAULT)
                if (player is ServerPlayerEntity) {
                    ModuledContentsComponent.iterateCopy().forEach(Consumer { stackx: ItemStack ->
                        player.dropItem(stackx, true)
                    })
                }
                return true
            } else return false
        }

        /*fun canBeScrolled(stack: ItemStack): Boolean {
            return true //stack.item is stack.isIn(CANBESCROLLED)
        }*/

        // Shifts contents of a moduled item
        fun shiftBundle(stack: ItemStack, shiftBy: Int) {
            val stacks: MutableList<ItemStack?>? = stack.get(ModComponents.BUNDLE_CONTENTS)?.stacks?.toMutableList()
            if (stacks != null) {
                val len: Int = stacks.size
                repeat(Math.floorMod(shiftBy, len)) { stacks.add(stacks.removeAt(0)) }
                stack.set(ModComponents.BUNDLE_CONTENTS, ModuledContentsComponent(stacks, stack.get(ModComponents.BUNDLE_CONTENTS)!!.occupancy))
            }
        }
    }
}
