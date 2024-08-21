package com.novvux.petrushka.render.screen.widget

import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.text.Text


class WindowWidget(x: Int, y: Int, width: Int, height: Int): ClickableWidget(x, y, width, height, Text.empty()) {
    override fun renderWidget(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        // We'll just draw a simple rectangle for now.
        // x1, y1, x2, y2, startColor, endColor
        val startColor = -0xff0100 // Green
        val endColor = -0xffff01 // Blue

        context.fillGradient(x, y, x + this.width, y + this.height, startColor, endColor)
    }

    override fun appendClickableNarrations(builder: NarrationMessageBuilder) {
        // For brevity, we'll just skip this for now - if you want to add narration to your widget, you can do so here.
        return
    }
}