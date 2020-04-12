package uk.co.renbinden.ilse.demo.net.packet.clientbound

import uk.co.renbinden.ilse.net.ClientboundPacket

data class ClientboundMovePacket(
    val playerId: Int,
    val x: Int,
    val y: Int,
    val dx: Int
) : ClientboundPacket