package uk.co.renbinden.ilse.demo.system

import uk.co.renbinden.ilse.demo.component.Position
import uk.co.renbinden.ilse.demo.component.Velocity
import uk.co.renbinden.ilse.demo.net.packet.serverbound.ServerboundMovePacket
import uk.co.renbinden.ilse.ecs.entity.Entity
import uk.co.renbinden.ilse.ecs.system.IteratingSystem
import uk.co.renbinden.ilse.net.Server
import kotlin.math.roundToInt
import kotlin.math.sign


class VelocitySystem(val server: Server) : IteratingSystem({
        it.has(Position)
                && it.has(Velocity)
}, priority = 4) {

    override fun processEntity(entity: Entity, dt: Double) {
        val velocity = entity[Velocity]
        val position = entity[Position]
        position.x += velocity.dx * dt
        position.y += velocity.dy * dt
        if (server.isConnected) {
            server.send(
                ServerboundMovePacket(
                    position.x.roundToInt(),
                    position.y.roundToInt(),
                    sign(velocity.dx).toInt()
                )
            )
        }
    }

}