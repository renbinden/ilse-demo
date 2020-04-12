package uk.co.renbinden.ilse.demo.archetype

import uk.co.renbinden.ilse.collision.RectangleCollider
import uk.co.renbinden.ilse.demo.component.Collider
import uk.co.renbinden.ilse.demo.component.Dimensions
import uk.co.renbinden.ilse.demo.component.Position
import uk.co.renbinden.ilse.ecs.entity.entity

object Block {

    operator fun invoke(x: Double, y: Double, width: Double, height: Double) = entity {
        add(Position(x, y))
        add(Dimensions(width, height))
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
    }

}