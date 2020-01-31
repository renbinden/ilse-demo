package uk.co.renbinden.ilse.demo.particle

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.particle.Particle
import uk.co.renbinden.ilse.particle.ParticleEffect
import kotlin.math.min
import kotlin.random.Random

class DustEffect(val assets: Assets, val canvas: HTMLCanvasElement, var x: Double, var y: Double) : ParticleEffect(0.5) {

    init {
        val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
        emitter(
            0.01,
            0.02,
            1,
            3
        ) {
            val targetX = x + Random.nextDouble(-32.0, 32.0)
            val targetY = y - Random.nextDouble(0.0, 32.0)
            var currentX = x
            var currentY = y
            var stateTime = 0.0
            val particle = Particle(
                0.2,
                onTick = { dt ->
                    stateTime += dt
                    val progress = min(stateTime, 0.2) / 0.2
                    currentX = ((x * (1.0 - progress)) + (targetX * progress))
                    currentY = ((y * (1.0 - progress)) + (targetY * progress))
                },
                onRender = {
                    ctx.drawImage(assets.images.dustParticle.image, currentX, currentY)
                }
            )
            return@emitter particle
        }
    }

}