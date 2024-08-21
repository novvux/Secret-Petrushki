package com.novvux.petrushka.component.moduled

import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import com.novvux.petrushka.SecretPetrushki
import net.fabricmc.fabric.api.tag.convention.v2.TagUtil.isIn
import net.minecraft.component.DataComponentTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipData
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.screen.slot.Slot
import net.minecraft.util.Identifier
import org.apache.commons.lang3.math.Fraction
import java.util.ArrayList
import kotlin.math.min


class ModuledContentsComponent internal constructor(val stacks: List<ItemStack>, val occupancy: Fraction): TooltipData {
    constructor(stacks: List<ItemStack>): this(stacks, calculateOccupancy(stacks))

    fun get(index: Int): ItemStack {
        return stacks[index]
    }

    val isEmpty: Boolean
        get() = stacks.isEmpty()

    override fun equals(other: Any?): Boolean {
        return if (this === other) true
        else if (other !is ModuledContentsComponent) false
        else this.occupancy == other.occupancy && ItemStack.stacksEqual(this.stacks, other.stacks)
    }

    override fun hashCode(): Int {
        return ItemStack.listHashCode(this.stacks)
    }

    override fun toString(): String {
        return "BundleContents$stacks"
    }

    /*
    fun stream(): Stream<ItemStack> {
        return Companion.stacks.stream().map { obj: ItemStack -> obj.copy() }
    }

    fun iterate(): Iterable<ItemStack> {
        return this.stacks
    }
    */

    fun iterateCopy(): Iterable<ItemStack> {
        return Lists.transform(this.stacks) { obj: ItemStack -> obj.copy() }
    }

    class Builder(base: ModuledContentsComponent) {
        private val stacks: MutableList<ItemStack> = ArrayList<ItemStack>(base.stacks)
        private var occupancy: Fraction

        init {
            this.occupancy = base.occupancy
        }

        fun clear(): Builder {
            stacks.clear()
            this.occupancy = Fraction.ZERO
            return this
        }

        private fun addInternal(stack: ItemStack): Int {
            if (!stack.isStackable) {
                return -1
            } else {
                for (i in stacks.indices) if (ItemStack.areItemsAndComponentsEqual(stacks[i], stack)) return i

                return -1
            }
        }

        private fun getMaxAllowed(stack: ItemStack): Int {
            /*val fraction = Fraction.ONE.subtract(this.occupancy)
            return max(fraction.divideBy(getOccupancy(stack)).toInt().toDouble(), 0.0).toInt()*/
            return 1
        }


        fun add(stack: ItemStack, slots: Byte?): Int {
            if (!stack.isEmpty && stack.item.canBeNested()) {
                val maxAllowed = min(stack.count.toFloat(), 1f).toInt()
                if (maxAllowed == 0) return 0
                else {
                    this.occupancy = occupancy.add(getOccupancy(stack).multiplyBy(Fraction.getFraction(maxAllowed, (slots ?: 2).toInt())))
                    val j = this.addInternal(stack)
                    if (j != -1) {
                        val itemStack = stacks.removeAt(j)
                        stack.decrement(maxAllowed)
                        stacks.add(0, itemStack.copyWithCount(itemStack.count + maxAllowed))
                    } else stacks.add(0, stack.split(maxAllowed))
                    return maxAllowed
                }
            } else return 0
        }

        fun add(slot: Slot, player: PlayerEntity, slots: Byte?): Int {
            return this.add(slot.takeStackRange(slot.stack.count, getMaxAllowed(slot.stack), player), slots)
        }

        fun removeFirst(): ItemStack? {
            if (stacks.isEmpty()) return null
            else {
                val itemStack = stacks.removeAt(0).copy()
                this.occupancy = occupancy.subtract(getOccupancy(itemStack).multiplyBy(Fraction.getFraction(itemStack.count, 1)))
                return itemStack
            }
        }

        fun build(): ModuledContentsComponent {
            return ModuledContentsComponent(java.util.List.copyOf(this.stacks), this.occupancy)
        }
    }

    companion object {
        //private val stacks: List<ItemStack> = stacks
        val DEFAULT: ModuledContentsComponent = ModuledContentsComponent(listOf())
        val CODEC: Codec<ModuledContentsComponent> = ItemStack.CODEC.listOf().xmap(
            { stacks: List<ItemStack> -> ModuledContentsComponent(stacks) },
            { component: ModuledContentsComponent -> component.stacks })
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, ModuledContentsComponent> = ItemStack.PACKET_CODEC.collect(PacketCodecs.toList()).xmap(
            { stacks: List<ItemStack> -> ModuledContentsComponent(stacks) },
            { component: ModuledContentsComponent -> component.stacks })
        private val NESTED_BUNDLE_OCCUPANCY: Fraction = Fraction.getFraction(1, 16)
        private const val ADD_TO_NEW_SLOT = -1
        private fun calculateOccupancy(stacks: List<ItemStack>): Fraction {
            var fraction = Fraction.ZERO

            var itemStack: ItemStack
            val var2: Iterator<*> = stacks.iterator()
            while (var2.hasNext()) {
                itemStack = var2.next() as ItemStack
                fraction = fraction.add(getOccupancy(itemStack).multiplyBy(Fraction.getFraction(itemStack.count, 1)))
            }
            return fraction
        }

        fun getOccupancy(stack: ItemStack): Fraction {
            val bundleContentsComponent = stack.get(DataComponentTypes.BUNDLE_CONTENTS)
            if (bundleContentsComponent != null) return NESTED_BUNDLE_OCCUPANCY.add(bundleContentsComponent.occupancy)
            else {
                val list: List<*> = stack.getOrDefault(DataComponentTypes.BEES, listOf<Any>()) as List<*>
                return if (list.isNotEmpty()) Fraction.ONE else Fraction.getFraction(1, 2)
            }
        }

        fun isThisModifier(entry: Item): Boolean {
            return isIn(TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "modificator")), entry)
        }
    }
}

/*
class ModuledContentsComponent internal constructor(val stacks: List<ItemStack>, val occupancy: Fraction): TooltipData {
    constructor(stacks: List<ItemStack>) : this(stacks, calculateOccupancy(stacks))

    fun get(index: Int): ItemStack? {
        return stacks[index]
    }

    override fun equals(other: Any?): Boolean {
        return if (this === other) true
        else if (other !is ModuledContentsComponent) false
        else this.occupancy == other.occupancy && ItemStack.stacksEqual(this.stacks, other.stacks)
    }

    @Suppress("DEPRECATION")
    override fun hashCode(): Int {
        return ItemStack.listHashCode(this.stacks)
    }

    override fun toString(): String {
        return "BundleContents$stacks"
    }

    class Builder(base: ModuledContentsComponent) {
        private val stacks: MutableList<ItemStack> = ArrayList<ItemStack>(base.stacks)
        var occupancy: Fraction = base.occupancy
            private set

        init { this.occupancy = base.occupancy }

        private fun addInternal(stack: ItemStack): Int {
            if (!stack.isStackable) return -1
            else {
                for (i in stacks.indices) {
                    if (ItemStack.areItemsAndComponentsEqual(stacks[i], stack)) return i
                }

                return -1
            }
        }

        fun removeFirst(): ItemStack? {
            if (stacks.isEmpty()) return null
            else {
                val itemStack = stacks.removeAt(0).copy()
                this.occupancy = occupancy.subtract(getOccupancy(itemStack).multiplyBy(Fraction.getFraction(itemStack.count, 1)))
                return itemStack
            }
        }

        fun add(stack: ItemStack, slots: Byte?): Byte {
            val varSlots: Byte = slots ?: 2
            if (!stack.isEmpty && stack.item.canBeNested() && isThisModifier(stack.item)) {
                if (stack.count == 0) return 0
                else {
                    this.occupancy = occupancy.add(getOccupancy(stack).multiplyBy(Fraction.getFraction(maxAllowed, varSlots.toInt())))
                    val j = this.addInternal(stack)
                    if (j != -1) {
                        val itemStack = stacks.removeAt(j)
                        val itemStack2 = itemStack.copyWithCount(itemStack.count + maxAllowed)
                        stack.decrement(maxAllowed)
                        stacks.add(0, itemStack2)
                    } else stacks.add(0, stack.split(maxAllowed))

                    return maxAllowed.toByte()
                }
            } else return 0
        }

        fun add(slot: Slot, player: PlayerEntity, slots: Byte?): Byte {
            return this.add(slot.takeStackRange(slot.stack.count, maxAllowed, player), slots)
        }

        fun build(): ModuledContentsComponent {
            return ModuledContentsComponent(java.util.List.copyOf(this.stacks), this.occupancy)
        }
    }

    companion object {
        const val maxAllowed: Int = 1
        private val stacks: List<ItemStack> = ModuledContentsComponent.stacks
        val DEFAULT: ModuledContentsComponent = ModuledContentsComponent(listOf(), Fraction.getFraction(1, 2))
        val CODEC: Codec<ModuledContentsComponent> = ItemStack.CODEC.listOf().xmap(
            { stacks: List<ItemStack> -> ModuledContentsComponent(stacks) },
            { component: ModuledContentsComponent -> component.stacks })
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, ModuledContentsComponent> = ItemStack.PACKET_CODEC.collect(PacketCodecs.toList()).xmap(
            { stacks: List<ItemStack> -> ModuledContentsComponent(stacks) },
            { component: ModuledContentsComponent -> component.stacks })
        private val NESTED_BUNDLE_OCCUPANCY: Fraction = Fraction.getFraction(1, 16)
        private const val ADD_TO_NEW_SLOT = -1
        private fun calculateOccupancy(stacks: List<ItemStack?>): Fraction {
            var fraction = Fraction.ZERO

            var itemStack: ItemStack
            val var2: Iterator<*> = stacks.iterator()
            while (var2.hasNext()) {
                itemStack = var2.next() as ItemStack
                fraction = fraction.add(getOccupancy(itemStack).multiplyBy(Fraction.getFraction(itemStack.count, 1)))
            }

            return fraction
        }

        fun getOccupancy(stack: ItemStack): Fraction {
            val bundleContentsComponent = stack.get(ModComponents.BUNDLE_CONTENTS) as ModuledContentsComponent?
            if (bundleContentsComponent != null) return NESTED_BUNDLE_OCCUPANCY.add(bundleContentsComponent.occupancy)
            else {
                val list: List<*> = stack.getOrDefault(DataComponentTypes.BEES, listOf<Any>()) as List<*>
                return if (list.isNotEmpty()) Fraction.ONE else Fraction.getFraction(1, 2)
            }
        }

        fun isThisModifier(entry: Item): Boolean {
            return isIn(TagKey.of(RegistryKeys.ITEM, Identifier.of(SecretPetrushki.MOD_ID, "modificator")), entry)
        }

        fun iterateCopy(): Iterable<ItemStack> {
            return Lists.transform(this.stacks) { obj: ItemStack? -> obj!!.copy() }
        }
    }
}
*/