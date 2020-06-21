package uk.co.renbinden.ilse.demo

import uk.co.renbinden.ilse.app.App
import uk.co.renbinden.ilse.demo.assets.Assets
import uk.co.renbinden.ilse.demo.screen.DemoScreen

@ExperimentalUnsignedTypes
@ExperimentalStdlibApi
fun main() {
    val app = App()
    val assets = Assets()
    val demoScreen = DemoScreen(app, assets)
    app.screen = demoScreen
}

