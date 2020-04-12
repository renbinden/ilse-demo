package uk.co.renbinden.ilse.demo.net.packet.clientbound

import org.khronos.webgl.DataView
import uk.co.renbinden.ilse.net.ClientboundPacket

data class ClientboundErrorPacket(val data: DataView) : ClientboundPacket