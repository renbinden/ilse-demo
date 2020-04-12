package uk.co.renbinden.ilse.demo.archetype

import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.demo.component.Animation
import uk.co.renbinden.ilse.demo.component.Position
import uk.co.renbinden.ilse.demo.component.RemotePlayerId
import uk.co.renbinden.ilse.ecs.entity.entity

object RemotePlayer {

    operator fun invoke(playerId: Int, assets: Assets) = entity {
        add(Position(0.0, 0.0, 0.0, 768.0, 0.0, 568.0))
        add(Animation(assets.animations.catWalkRight, 0.5))
        add(RemotePlayerId(playerId))
    }

}