package com.novvux.petrushka.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.render.Camera
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.texture.Sprite
import net.minecraft.client.world.ClientWorld
import net.minecraft.util.math.MathHelper
import org.joml.Quaternionf
import org.joml.Vector3f

@Environment(EnvType.CLIENT)
open class DirectionalParticle protected constructor(
    world: ClientWorld?,
    x: Double,
    y: Double,
    z: Double,
    spriteProvider: SpriteProvider,
): Particle(world, x, y, z) {
    protected var scale: Float = 0.0f
    protected var providedSprite: Sprite? = null

    override fun getType(): ParticleTextureSheet {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT
    }

    override fun tick() {
        super.tick()
        if (this.age > this.maxAge / 2) {
            this.setAlpha(1.0f - (age.toFloat() - (this.maxAge / 2).toFloat()) / maxAge.toFloat())
        }

    }

    override fun buildGeometry(vertexConsumer: VertexConsumer, camera: Camera, tickDelta: Float) {
        val quaternionf = Quaternionf()
        //this.getRotator().setRotation(quaternionf, camera, tickDelta)

        this.renderRotatedQuad(vertexConsumer, camera, quaternionf, tickDelta)
    }

    protected fun renderRotatedQuad(vertexConsumer: VertexConsumer, camera: Camera, quaternionf: Quaternionf, tickDelta: Float) {
        val vec3d = camera.pos
        val posX = (MathHelper.lerp(tickDelta.toDouble(), this.prevPosX, this.x) - vec3d.getX()).toFloat()
        val posY = (MathHelper.lerp(tickDelta.toDouble(), this.prevPosY, this.y) - vec3d.getY()).toFloat()
        val posZ = (MathHelper.lerp(tickDelta.toDouble(), this.prevPosZ, this.z) - vec3d.getZ()).toFloat()
        this.renderRotatedQuad(vertexConsumer, quaternionf, posX, posY, posZ, tickDelta)
    }

    protected fun renderRotatedQuad(vertexConsumer: VertexConsumer, quaternionf: Quaternionf, posX: Float, posY: Float, posZ: Float, tickDelta: Float) {
        val size = this.getSize()
        val minU = this.getMinU()
        val maxU = this.getMaxU()
        val minV = this.getMinV()
        val maxV = this.getMaxV()
        val brightness = this.getBrightness(tickDelta)
        this.renderVertex(vertexConsumer, quaternionf, posX, posY, posZ, 1.0f, -1.0f, size, maxU, maxV, brightness)
        this.renderVertex(vertexConsumer, quaternionf, posX, posY, posZ, 1.0f, 1.0f, size, maxU, minV, brightness)
        this.renderVertex(vertexConsumer, quaternionf, posX, posY, posZ, -1.0f, 1.0f, size, minU, minV, brightness)
        this.renderVertex(vertexConsumer, quaternionf, posX, posY, posZ, -1.0f, -1.0f, size, minU, maxV, brightness)
    }

    private fun renderVertex(vertexConsumer: VertexConsumer, quaternionf: Quaternionf, posX: Float, posY: Float, posZ: Float, i: Float, j: Float, size: Float, U: Float, V: Float, brightness: Int) {
        val vector3f = Vector3f(i, j, 0.0f).rotate(quaternionf).mul(size).add(posX, posY, posZ)
        vertexConsumer.vertex(vector3f.x(), vector3f.y(), vector3f.z()).texture(U, V).color(this.red, this.green, this.blue, this.alpha).light(brightness)
    }

    protected fun setSprite(providedSprite: Sprite) { this.providedSprite = providedSprite }

    protected fun getMinU(): Float { return this.providedSprite!!.getMinU() }
    protected fun getMaxU(): Float { return this.providedSprite!!.getMaxU() }
    protected fun getMinV(): Float { return this.providedSprite!!.getMinV() }
    protected fun getMaxV(): Float { return this.providedSprite!!.getMaxV() }

    fun setSpriteForAge(spriteProvider: SpriteProvider) {
        if (!this.dead) {
            this.setSprite(spriteProvider.getSprite(this.age, this.maxAge))
        }
    }

    open fun getSize(): Float { return this.scale }

    override fun scale(scale: Float): Particle {
        this.scale *= scale
        return super.scale(scale)
    }

    /*
    TODO: Make particle rotate
     */
}