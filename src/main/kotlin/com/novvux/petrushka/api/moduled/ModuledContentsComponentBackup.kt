package com.novvux.petrushka.api.moduled

import com.google.common.collect.Lists
import com.mojang.serialization.Codec
import com.novvux.petrushka.component.ModComponents
import com.novvux.petrushka.component.ModDataComponentTypes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipData
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.screen.slot.Slot
import org.apache.commons.lang3.math.Fraction
import java.util.ArrayList
import java.util.stream.Stream
import kotlin.math.max
import kotlin.math.min

/*
class ModuledContentsComponent internal constructor(val stacks: List<ItemStack>, val occupancy: Fraction): TooltipData {
    constructor(stacks: List<ItemStack>, bundleSize: Byte) : this(stacks, calculateOccupancy(stacks, bundleSize))

    fun get(index: Int): ItemStack {
        return stacks[index]
    }

    override fun equals(other: Any?): Boolean {
        return if (this === other) {
            true
        } else if (other !is ModuledContentsComponent) {
            false
        } else {
            this.occupancy == other.occupancy && ItemStack.stacksEqual(
                this.stacks,
                other.stacks
            )
        }
    }

    override fun hashCode(): Int {
        return ItemStack.listHashCode(this.stacks)
    }

    override fun toString(): String {
        return "BundleContents$stacks"
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
                for (i in stacks.indices) {
                    if (ItemStack.areItemsAndComponentsEqual(stacks[i], stack)) {
                        return i
                    }
                }
                return -1
            }
        }

        private fun getMaxAllowed(stack: ItemStack): Int {
            val fraction = Fraction.ONE.subtract(this.occupancy)
            return max(fraction.divideBy(getOccupancy(stack)).toInt().toDouble(), 0.0).toInt()
        }

        fun add(stack: ItemStack): Int {
            if (!stack.isEmpty && stack.item.canBeNested()) {
                val i =
                    min(stack.count.toDouble(), getMaxAllowed(stack).toDouble()).toInt()
                if (i == 0) {
                    return 0
                } else {
                    this.occupancy = occupancy.add(getOccupancy(stack).multiplyBy(Fraction.getFraction(i, 1)))
                    val j = this.addInternal(stack)
                    if (j != -1) {
                        val itemStack = stacks.removeAt(j)
                        val itemStack2 = itemStack.copyWithCount(itemStack.count + i)
                        stack.decrement(i)
                        stacks.add(0, itemStack2)
                    } else {
                        stacks.add(0, stack.split(i))
                    }

                    return i
                }
            } else {
                return 0
            }
        }

        fun add(slot: Slot, player: PlayerEntity): Int {
            val itemStack = slot.stack
            val i = this.getMaxAllowed(itemStack)
            return this.add(slot.takeStackRange(itemStack.count, i, player))
        }

        fun removeFirst(): ItemStack? {
            if (stacks.isEmpty()) {
                return null
            } else {
                val itemStack = stacks.removeAt(0).copy()
                this.occupancy =
                    occupancy.subtract(getOccupancy(itemStack).multiplyBy(Fraction.getFraction(itemStack.count, 1)))
                return itemStack
            }
        }

        fun build(): ModuledContentsComponent {
            return ModuledContentsComponent(java.util.List.copyOf(this.stacks), this.occupancy)
        }
    }

    companion object {
        private val stacks: List<ItemStack> = ModuledContentsComponent.stacks
        val DEFAULT: ModuledContentsComponent = ModuledContentsComponent(listOf(), 64)
        val CODEC: Codec<ModuledContentsComponent> = ItemStack.CODEC.listOf().xmap(
            { stacks: List<ItemStack> -> ModuledContentsComponent(stacks, 1) },
            { component: ModuledContentsComponent -> component.stacks }
        )
        val PACKET_CODEC: PacketCodec<RegistryByteBuf, ModuledContentsComponent> = ItemStack.PACKET_CODEC.collect(
            PacketCodecs.toList()).xmap(
            { stacks: List<ItemStack> -> ModuledContentsComponent(stacks, 1) },
            { component: ModuledContentsComponent -> component.stacks }
        )
        private val NESTED_BUNDLE_OCCUPANCY: Fraction = Fraction.getFraction(1, 16)
        private const val ADD_TO_NEW_SLOT = -1
        private fun calculateOccupancy(stacks: List<ItemStack?>, bundleSize: Byte?): Fraction {
            var fraction = Fraction.ZERO

            var itemStack: ItemStack
            val var2: Iterator<*> = stacks.iterator()
            while (var2.hasNext()) {
                itemStack = var2.next() as ItemStack
                if (bundleSize != null) fraction = fraction.add(getOccupancy(itemStack).multiplyBy(Fraction.getFraction(bundleSize.toInt(), 1)))
            }

            return fraction
        }

        fun getOccupancy(stack: ItemStack): Fraction {
            val bundleContentsComponent = stack.get(ModDataComponentTypes.BUNDLE_CONTENTS)
            val bundleSize: Byte? = stack.get(ModComponents.UPGRADE_SLOTS)
            if (bundleContentsComponent != null) return NESTED_BUNDLE_OCCUPANCY.add(bundleContentsComponent.occupancy)
            else if (bundleSize != null) return Fraction.getFraction(1, bundleSize.toInt())
            return Fraction.ONE
        }

        fun stream(): Stream<ItemStack> {
            return stacks.stream().map { obj: ItemStack -> obj.copy() }
        }

        fun size(): Int {
            return stacks.size
        }

        val isEmpty: Boolean get() = stacks.isEmpty()

        fun iterate(): Iterable<ItemStack> {
            return this.stacks
        }

        fun iterateCopy(): Iterable<ItemStack> {
            return Lists.transform(
                this.stacks
            ) { obj: ItemStack? -> obj!!.copy() }
        }

    }
}
*/