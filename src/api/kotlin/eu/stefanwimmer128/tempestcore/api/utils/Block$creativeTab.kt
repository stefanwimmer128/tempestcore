package eu.stefanwimmer128.tempestcore.api.utils

import net.minecraft.block.Block

/**
 * Provides alias to use Kotlin getter and setter for [Block]'s creativeTab
 */
var Block.creativeTab
    get() =
        creativeTabToDisplayOn
    set(value) {
        setCreativeTab(value)
    }
