package uk.co.renbinden.ilse.demo.component

import uk.co.renbinden.ilse.asset.ImageAsset
import uk.co.renbinden.ilse.ecs.component.Component
import uk.co.renbinden.ilse.ecs.component.ComponentMapper


class Image(
    val asset: ImageAsset,
    val sourceX: Double = 0.0,
    val sourceY: Double = 0.0,
    val sourceWidth: Double = asset.image.naturalWidth.toDouble(),
    val sourceHeight: Double = asset.image.naturalHeight.toDouble(),
    val destX: Double = 0.0,
    val destY: Double = 0.0,
    val destWidth: Double = asset.image.naturalWidth.toDouble(),
    val destHeight: Double = asset.image.naturalHeight.toDouble()
) : Component {

    constructor(
        asset: ImageAsset,
        sourceX: Int = 0,
        sourceY: Int = 0,
        sourceWidth: Int = asset.image.naturalWidth,
        sourceHeight: Int = asset.image.naturalHeight,
        destX: Int = 0,
        destY: Int = 0,
        destWidth: Int = asset.image.naturalWidth,
        destHeight: Int = asset.image.naturalHeight
    ): this(
        asset,
        sourceX.toDouble(),
        sourceY.toDouble(),
        sourceWidth.toDouble(),
        sourceHeight.toDouble(),
        destX.toDouble(),
        destY.toDouble(),
        destWidth.toDouble(),
        destHeight.toDouble()
    )

    companion object: ComponentMapper<Image>(Image::class)

}