package uk.co.renbinden.ilse.demo.system

import uk.co.renbinden.ilse.demo.component.Effect
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.system.IteratingSystem

class ParticleSystemSystem : IteratingSystem({
    it.has(Effect)
}) {
    override fun processEntity(entity: Entity, dt: Double) {
        val particleEffect = entity[Effect].particleEffect
        particleEffect.onTick(dt)
        if (particleEffect.life <= 0.0) {
            entity.remove(Effect)
        }
    }
}