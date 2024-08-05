package com.novvux.petrushka.mixin.client;


import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(HandledScreen.class)
public interface IHandledScreenInvoker {
    @Invoker("getSlotAt")
    public Slot invokeGetSlotAt(double x, double y);
}