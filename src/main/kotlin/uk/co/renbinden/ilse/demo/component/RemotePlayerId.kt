package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper

data class RemotePlayerId(val playerId: Int) : Component {
    companion object : ComponentMapper<RemotePlayerId>(RemotePlayerId::class)
}