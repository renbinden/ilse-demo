package uk.co.renbinden.ilse.demo.net

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.DataView
import uk.co.renbinden.ilse.demo.net.packet.clientbound.ClientboundErrorPacket
import uk.co.renbinden.ilse.demo.net.packet.clientbound.ClientboundJoinPacket
import uk.co.renbinden.ilse.demo.net.packet.clientbound.ClientboundLeavePacket
import uk.co.renbinden.ilse.demo.net.packet.clientbound.ClientboundMovePacket
import uk.co.renbinden.ilse.net.ClientboundPacket

class ClientboundPacketDeserializer : ClientboundPacket.Deserializer {

    override fun deserialize(data: ArrayBuffer): ClientboundPacket {
        val view = DataView(data)
        return when (view.getInt32(0)) {
            0 -> ClientboundJoinPacket(
                view.getInt32(4)
            )
            1 -> ClientboundLeavePacket(
                view.getInt32(4)
            )
            2 -> ClientboundMovePacket(
                view.getInt32(4),
                view.getInt32(8),
                view.getInt32(12),
                view.getInt32(16)
            )
            else -> ClientboundErrorPacket(view)
        }
    }

}