package com.novvux.petrushka.particle

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.render.Camera
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.texture.Sprite
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.MathHelper
import org.joml.Quaternionf
import org.joml.Vector3f

@Environment(EnvType.CLIENT)
abstract class PreDirectionalParticle: Particle {
    protected var scale: Float
    protected var providedSprite: Sprite? = null

    protected constructor(clientWorld: ClientWorld?, d: Double, e: Double, f: Double) : super(clientWorld, d, e, f) {
        this.scale = 0.1f * (random.nextFloat() * 0.5f + 0.5f) * 2.0f
    }

    protected constructor(
        clientWorld: ClientWorld?,
        d: Double,
        e: Double,
        f: Double,
        g: Double,
        h: Double,
        i: Double
    ) : super(clientWorld, d, e, f, g, h, i) {
        this.scale = 0.1f * (random.nextFloat() * 0.5f + 0.5f) * 2.0f
    }

    //open val rotator: Rotator get() = Rotator.Y_AND_W_ONLY

    override fun buildGeometry(vertexConsumer: VertexConsumer, camera: Camera, tickDelta: Float) {
        val quaternionf = Quaternionf()
        val vec3d = camera.pos
        val posX = (this.x - vec3d.getX()).toFloat()
        val posY = (this.y - vec3d.getY()).toFloat()
        val posZ = (this.z - vec3d.getZ()).toFloat()
        //val posX = (MathHelper.lerp(tickDelta.toDouble(), this.prevPosX, this.x) - vec3d.getX()).toFloat()
        //val posY = (MathHelper.lerp(tickDelta.toDouble(), this.prevPosY, this.y) - vec3d.getY()).toFloat()
        //val posZ = (MathHelper.lerp(tickDelta.toDouble(), this.prevPosZ, this.z) - vec3d.getZ()).toFloat()
        val size = this.getSize(posZ)
        val minU = this.getMinU()
        val maxU = this.getMaxU()
        val minV = this.getMinV()
        val maxV = this.getMaxV()
        val brightness = this.getBrightness(posZ)
        ParticleProperties.setRotation(quaternionf, camera, tickDelta)

        //quaternionf.rotateZ(90f)
        //quaternionf.rotateX(90f)
        //quaternionf.rotateY(90f)

        this.renderVertex(vertexConsumer, quaternionf, tickDelta, posX, posY, 1.0f, -1.0f, size, maxU, maxV, brightness)
        this.renderVertex(vertexConsumer, quaternionf, tickDelta, posX, posY, 1.0f, 1.0f, size, maxU, minV, brightness)
        this.renderVertex(vertexConsumer, quaternionf, tickDelta, posX, posY, -1.0f, 1.0f, size, minU, minV, brightness)
        this.renderVertex(vertexConsumer, quaternionf, tickDelta, posX, posY, -1.0f, -1.0f, size, minU, maxV, brightness)
    }

    private fun renderVertex(vertexConsumer: VertexConsumer, quaternionf: Quaternionf?, f: Float, g: Float, h: Float, i: Float, j: Float, size: Float, U: Float, V: Float, brightness: Int) {
        val vector3f = Vector3f(i, j, 0.0f).rotate(quaternionf).mul(size).add(f, g, h)
        vertexConsumer.vertex(vector3f.x(), vector3f.y(), vector3f.z()).texture(U, V).color(this.red, this.green, this.blue, this.alpha).light(brightness)
    }

    protected fun setSprite(providedSprite: Sprite) { this.providedSprite = providedSprite }

    protected fun getMinU(): Float { return this.providedSprite!!.getMinU() }
    protected fun getMaxU(): Float { return this.providedSprite!!.getMaxU() }
    protected fun getMinV(): Float { return this.providedSprite!!.getMinV() }
    protected fun getMaxV(): Float { return this.providedSprite!!.getMaxV() }

    fun setSprite(spriteProvider: SpriteProvider) {
        this.setSprite(spriteProvider.getSprite(this.random));
    }

    fun setSpriteForAge(spriteProvider: SpriteProvider) {
        if (!this.dead) {
            this.setSprite(spriteProvider.getSprite(this.age, this.maxAge))
        }
    }

    open fun getSize(tickDelta: Float): Float {
        return this.scale
    }

    override fun scale(scale: Float): Particle {
        this.scale *= scale
        return super.scale(scale)
    }

    interface ParticleProperties {
        companion object {
            fun setRotation(quaternionf: Quaternionf, camera: Camera, tickDelta: Float) {

            }
        }
    }
}
