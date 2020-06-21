package uk.co.renbinden.ilse.demo.screen

import uk.co.renbinden.ilse.app.App
import uk.co.renbinden.ilse.app.screen.Screen
import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.ecs.engine
import uk.co.renbinden.ilse.event.Events
import uk.co.renbinden.ilse.event.Listener
import uk.co.renbinden.ilse.input.event.MouseDownEvent

class TestScreen(val app: App, val assets: Assets) : Screen(engine {

}) {
    val mouseDownListener = Listener<MouseDownEvent>({ event ->
        console.log("Click")
    })

    init {
        Events.addListener(MouseDownEvent, mouseDownListener)
    }
    override fun onRender() {

    }
}