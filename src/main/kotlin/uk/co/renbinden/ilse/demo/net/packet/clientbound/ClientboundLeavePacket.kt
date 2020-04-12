package uk.co.renbinden.ilse.demo.net.packet.clientbound

import uk.co.renbinden.ilse.net.ClientboundPacket

data class ClientboundLeavePacket(val playerId: Int) : ClientboundPacket