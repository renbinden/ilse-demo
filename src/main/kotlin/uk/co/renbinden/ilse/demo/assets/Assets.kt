package uk.co.renbinden.ilse.demo.assets

import uk.co.renbinden.ilse.asset.AnimationAsset
import uk.co.renbinden.ilse.asset.ImageAsset
import uk.co.renbinden.ilse.asset.SoundAsset
import uk.co.renbinden.ilse.asset.TextAsset


class Assets {

    inner class Sounds {
        val coins = SoundAsset("static/sounds/coin.wav")
    }

    inner class Images {
        val block = ImageAsset("static/images/block.png")
    }

    inner class Animations {
        val catWalkRight = AnimationAsset("static/images/cat_walk_right.png", 32, 32)
        val catWalkLeft = AnimationAsset("static/images/cat_walk_left.png", 32, 32)
    }

    inner class Maps {
        val demoLevel = TextAsset("static/levels/demo_level.tmx")
    }

    val sounds = Sounds()
    val images = Images()
    val animations = Animations()
    val maps = Maps()

}