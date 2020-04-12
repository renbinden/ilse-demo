package uk.co.renbinden.ilse.demo.archetype

import org.w3c.dom.HTMLCanvasElement
import uk.co.renbinden.ilse.collision.RectangleCollider
import uk.co.renbinden.ilse.collision.event.VerticalCollisionEvent
import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.demo.component.*
import uk.co.renbinden.ilse.demo.particle.DustEffect
import uk.co.renbinden.ilse.ecs.entity.entity
import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.input.mapping.Keyboard
import uk.co.renbinden.ilse.input.mapping.XBoxOneGamepad

object Player {

    operator fun invoke(x: Double, y: Double, assets: Assets, canvas: HTMLCanvasElement) = entity {
        add(Position(x, y, 0.0, 768.0, 0.0, 568.0))
        add(Velocity(0.0, 0.0, 240.0, 720.0))
        add(Acceleration(0.0, 320.0, 0.0, 32.0))
        add(Dimensions(32.0, 32.0))
        add(
            Collider(
                RectangleCollider(
                    get(Position)::x,
                    get(Position)::y,
                    get(Dimensions)::width,
                    get(Dimensions)::height
                )
            )
        )
        add(Animation(assets.animations.catWalkRight, 0.5))
        add(
            Controls(
                leftKey = Keyboard.ARROW_LEFT,
                rightKey = Keyboard.ARROW_RIGHT,
                jumpKey = Keyboard.ARROW_UP,
                gamepadHorizontalAxes = arrayOf(
                    XBoxOneGamepad.Axis.LEFT_STICK_HORIZONTAL_AXIS,
                    XBoxOneGamepad.Axis.DPAD_HORIZONTAL_AXIS
                ),
                gamepadJumpButton = XBoxOneGamepad.Button.A
            )
        )
        add(JumpedSinceLastCollision())
        Events.addListener(VerticalCollisionEvent, { event -> event.collider == get(Collider).collider }) {
            if (!has(Effect) && has(JumpedSinceLastCollision)) {
                add(
                    Effect(
                        DustEffect(
                            assets,
                            canvas,
                            get(Position).x + 16,
                            get(Position).y + 32
                        )
                    )
                )
                remove(JumpedSinceLastCollision)
            }
        }
    }

}