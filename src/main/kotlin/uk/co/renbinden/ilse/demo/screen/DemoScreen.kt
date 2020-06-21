package uk.co.renbinden.ilse.demo.screen

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import uk.co.renbinden.ilse.app.App
import uk.co.renbinden.ilse.app.screen.Screen
import uk.co.renbinden.ilse.demo.archetype.Block
import uk.co.renbinden.ilse.demo.archetype.Player
import uk.co.renbinden.ilse.demo.archetype.RemotePlayer
import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.demo.component.*
import uk.co.renbinden.ilse.demo.level.loadLevel
import uk.co.renbinden.ilse.demo.net.ClientboundPacketDeserializer
import uk.co.renbinden.ilse.demo.net.packet.clientbound.ClientboundJoinPacket
import uk.co.renbinden.ilse.demo.net.packet.clientbound.ClientboundLeavePacket
import uk.co.renbinden.ilse.demo.net.packet.clientbound.ClientboundMovePacket
import uk.co.renbinden.ilse.demo.system.*
import uk.co.renbinden.ilse.ecs.engine
import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.event.Listener
import uk.co.renbinden.ilse.input.event.KeyDownEvent
import uk.co.renbinden.ilse.input.mapping.Keyboard.BACKTICK
import uk.co.renbinden.ilse.input.mapping.Keyboard.SPACE
import uk.co.renbinden.ilse.net.ClientboundPacket
import uk.co.renbinden.ilse.net.Server
import uk.co.renbinden.ilse.net.event.PacketReceivedEvent
import kotlin.browser.document


@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
class DemoScreen(private val app: App, private val assets: Assets) : Screen(
    engine {
        add(ControlSystem(assets))
        add(AccelerationSystem())
        add(CollisionSystem())
        add(AnimationSystem())
        add(ParticleSystemSystem())
    }
) {

    val canvas = document.getElementById("canvas") as HTMLCanvasElement
    val ctx = canvas.getContext("2d") as CanvasRenderingContext2D

    val server = Server("ws://localhost:9000")

    var debug = false

    private val keyDownListener = Listener<KeyDownEvent>({ event ->
        when (event.keyCode) {
            SPACE -> assets.sounds.coins.play()
            BACKTICK -> {
                debug = !debug
            }
        }
    })

    private val packetReceivedListener = Listener<PacketReceivedEvent>({ event ->
        val packet = event.packet
        when (packet) {
            is ClientboundJoinPacket -> engine.add(RemotePlayer(packet.playerId, assets))
            is ClientboundLeavePacket ->
                engine.entities.removeAll {
                    it.has(RemotePlayerId)
                            && it[RemotePlayerId].playerId == packet.playerId
                }
            is ClientboundMovePacket -> engine.entities
                .filter {
                    it.has(RemotePlayerId)
                            && it[RemotePlayerId].playerId == packet.playerId
                            && it.has(Position)
                            && it.has(Animation)
                }
                .forEach { player ->
                    val position = player[Position]
                    position.x = packet.x.toDouble()
                    position.y = packet.y.toDouble()
                    val animation = player[Animation]
                    if (packet.dx > 0) {
                        animation.asset = assets.animations.catWalkRight
                    } else if (packet.dx < 0) {
                        animation.asset = assets.animations.catWalkLeft
                    }
                }
        }
    })

    init {
        engine.add(VelocitySystem(server))
        engine.loadLevel(
            assets.maps.demoLevel,
            { imageSource ->
                when (imageSource) {
                    "../images/block.png" -> assets.images.block
                    else -> null
                }
            },
            { obj ->
                when (obj.type) {
                    "player" -> Player(obj.x, obj.y, assets, canvas)
                    "block" -> Block(obj.x, obj.y, obj.width, obj.height)
                    else -> null
                }
            }
        )

        ClientboundPacket.deserializer = ClientboundPacketDeserializer()

        // Listeners need to be initialized above where addListeners() is called!!
        // Otherwise you get cryptic errors
        addListeners()
    }

    private fun addListeners() {
        // Careful if you have multiple screens
        // Event listeners persist across screens and must be removed if no longer relevant.
        Events.addListener(KeyDownEvent, keyDownListener)
        Events.addListener(PacketReceivedEvent, packetReceivedListener)
    }

    private fun removeListeners() {
        Events.removeListener(KeyDownEvent, keyDownListener)
        Events.removeListener(PacketReceivedEvent, packetReceivedListener)
    }

    override fun onRender() {
        ctx.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        ctx.fillStyle = "rgb(0, 0, 0)"
        ctx.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        engine.entities
            .filter { entity -> entity.has(Position) && entity.has(Image) }
            .forEach { entity ->
                val position = entity[Position]
                val image = entity[Image]
                if (image.asset.isLoaded) {
                    ctx.drawImage(image.asset.image, position.x, position.y)
                }
            }

        engine.entities
            .filter { entity -> entity.has(Position) && entity.has(Animation) }
            .forEach { entity ->
                val position = entity[Position]
                val animation = entity[Animation]
                if (animation.asset.isLoaded) {
                    animation.asset.drawFrame(animation.getFrame(), ctx, position.x, position.y)
                }
            }

        engine.entities
            .filter { entity -> entity.has(Effect) }
            .forEach { entity ->
                val particleComponent = entity[Effect]
                particleComponent.particleEffect.onRender()
            }

        if (debug) {
            engine.entities
                .filter { entity -> entity.has(Collider) }
                .forEach { entity ->
                    val collider = entity[Collider].collider
                    ctx.strokeStyle = "rgb(255, 0, 0)"
                    ctx.strokeRect(collider.x, collider.y, collider.width, collider.height)
                }
        }
    }

}