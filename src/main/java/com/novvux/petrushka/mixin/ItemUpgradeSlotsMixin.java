package com.novvux.petrushka.mixin;

import com.novvux.petrushka.item.custom.moduled.ModuleItem;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.item.ItemStack;
import com.novvux.petrushka.component.ModComponents;

import java.util.List;

@Mixin(ModuleItem.class)
public class ItemUpgradeSlotsMixin {
    /*@Inject(at = @At("RETURN"), target = "Lnet", method = "")
    private void init(ItemStack stack) {
        Byte SLOTS = stack.get(ModComponents.UPGRADE_SLOTS);
        // This code is injected into the start of MinecraftServer.loadWorld()V
    }*/

    /*@Inject(at = @At("INVOKE"), target = "Lcom/novvux/petrushka/item/custom/moduled", method = "appendTooltipV")
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        Byte SLOTS = stack.get(ModComponents.UPGRADE_SLOTS);
        if (bundleContentsComponent != null) {
            int i = MathHelper.multiplyFraction(bundleContentsComponent.getOccupancy(), 64);
            tooltip.add(Text.translatable("item.minecraft.bundle.fullness", new Object[]{i, 64}).formatted(Formatting.GRAY));
        }
    }*/

}