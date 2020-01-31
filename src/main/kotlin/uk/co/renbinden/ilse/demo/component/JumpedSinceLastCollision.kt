package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper

class JumpedSinceLastCollision : Component {
    companion object : ComponentMapper<JumpedSinceLastCollision>(JumpedSinceLastCollision::class)
}