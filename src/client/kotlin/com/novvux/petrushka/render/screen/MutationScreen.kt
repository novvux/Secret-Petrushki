package com.novvux.petrushka.render.screen

import com.novvux.petrushka.render.screen.widget.WindowWidget
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.text.Text


class MutationScreen(title: Text /*private val parent: Screen*/) : Screen(title) {
    /*override fun close() {
        this.client!!.setScreen(this.parent)
    }*/

    override fun init() {
        val buttonWidget: ButtonWidget = ButtonWidget.builder(Text.of("Hello World")) {
            // When the button is clicked, we can display a toast to the screen.
            /*
            client!!.toastManager.add(
                SystemToast.create(
                    this.client,
                    SystemToast.Type.NARRATOR_TOGGLE,
                    Text.of("Hello World!"),
                    Text.of("This is a toast.")
                )
            )
            */
            close()
        }.dimensions(this.width/4, this.height/8, this.width/2, this.height/8).build()//40, 40, 120, 20).build()

        val containerWidget = null
        val windowWidget: WindowWidget = WindowWidget(40, 80, 120, 20)

        // Register the widget.
        this.addDrawableChild(windowWidget)
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        super.render(context, mouseX, mouseY, delta)

        // Minecraft doesn't have a "label" widget, so we'll have to draw our own text.
        // We'll subtract the font height from the Y position to make the text appear above the button.
        // Subtracting an extra 10 pixels will give the text some padding.
        // textRenderer, text, x, y, color, hasShadow
        context.drawText(this.textRenderer, "Special Button", 40, 40 - textRenderer.fontHeight - 10, -0x1, true)
    }
}