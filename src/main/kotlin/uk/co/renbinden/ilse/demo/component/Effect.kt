package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper
import uk.co.renbinden.ilse.particle.ParticleEffect

class Effect(val particleEffect: ParticleEffect) : Component {
    companion object : ComponentMapper<Effect>(Effect::class)
}