package uk.co.renbinden.ilse.demo.net.packet.serverbound

import org.khronos.webgl.ArrayBuffer
import org.khronos.webgl.Int8Array
import uk.co.renbinden.ilse.net.ServerboundPacket
import uk.co.renbinden.ilse.net.toByteArray

class ServerboundMovePacket(val x: Int, val y: Int, val dx: Int) : ServerboundPacket {
    override fun serialize(): ArrayBuffer {
        return Int8Array(arrayOf(
            *0.toByteArray(),
            *x.toByteArray(),
            *y.toByteArray(),
            *dx.toByteArray()
        )).buffer
    }
}