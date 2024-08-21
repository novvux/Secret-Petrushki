package com.novvux.petrushka.render.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.novvux.petrushka.handler.FleshBoxScreenHandler
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier


open class FleshBoxScreen(handler: FleshBoxScreenHandler, inventory: PlayerInventory?, title: Text?):
    HandledScreen<FleshBoxScreenHandler>(handler, inventory, title) {
    //A path to the gui texture. In this example we use the texture from the dispenser
    private val TEXTURE = Identifier.of("minecraft", "textures/gui/container/dispenser.png")
    protected fun drawBackground(matrices: MatrixStack?, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader { GameRenderer.getPositionTexProgram() }
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2
        //DrawContext.drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight)
        //in 1.20 or above,this method is in DrawContext class.
    }

    fun render(matrices: MatrixStack?, mouseX: Int, mouseY: Int, delta: Float) {
        /*
        renderBackground(matrices)
        super.render(matrices, mouseX, mouseY, delta)
        drawMouseoverTooltip(matrices, mouseX, mouseY)
        */
    }

    override fun init() {
        super.init()
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2
    }

    override fun drawBackground(context: DrawContext?, delta: Float, mouseX: Int, mouseY: Int) {
        TODO("Not yet implemented")
    }
}

