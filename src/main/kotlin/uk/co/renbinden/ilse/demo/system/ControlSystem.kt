package uk.co.renbinden.ilse.demo.system

import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.demo.component.*
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.system.IteratingSystem
import uk.co.renbinden.ilse.input.Input


class ControlSystem(private val assets: Assets) : IteratingSystem({
    it.has(Controls)
            && it.has(Position)
            && it.has(Velocity)
            && it.has(Collider)
}, priority = 1) {

    override fun processEntity(entity: Entity, dt: Double) {
        val controls = entity[Controls]
        val position = entity[Position]
        val velocity = entity[Velocity]
        val collider = entity[Collider]
        velocity.dx = 0.0
        if ((Input.isKeyPressed(controls.jumpKey)
                    || (Input.gamepads.isNotEmpty() && Input.gamepads[0].isButtonPressed(controls.gamepadJumpButton)))
            && collider.collider.test(position.x, position.y + 1)
                .collidesWith(engine.entities.filter { it.has(Collider) && it != entity }.map { it[Collider].collider })
        ) {
            velocity.dy -= 240.0
        }
        if (Input.isKeyPressed(controls.leftKey)
            || (Input.gamepads.isNotEmpty() && (controls.gamepadHorizontalAxes.any { Input.gamepads[0].getAxisValue(it) < -0.3 }))) {
            velocity.dx -= 240.0
            if (entity.has(Animation) && entity[Animation].asset == assets.animations.catWalkRight) {
                entity.remove(Animation)
                entity.add(Animation(assets.animations.catWalkLeft, 0.5))
            }
        }
        if (Input.isKeyPressed(controls.rightKey)
            || (Input.gamepads.isNotEmpty() && (controls.gamepadHorizontalAxes.any { Input.gamepads[0].getAxisValue(it) > 0.3 }))) {
            velocity.dx += 240.0
            if (entity.has(Animation) && entity[Animation].asset == assets.animations.catWalkLeft) {
                entity.remove(Animation)
                entity.add(Animation(assets.animations.catWalkRight, 0.5))
            }
        }
    }

}