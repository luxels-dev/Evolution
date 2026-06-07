package com.helydra.evolutionPlugin.utils

import org.bukkit.block.Block
import com.helydra.evolutionPlugin.data as pluginData

fun Block.mark(boolean: Boolean) {
    if (boolean) pluginData.set("mark.${world.name};$x;$y;$z", true)
    else pluginData.remove("mark.${world.name};$x;$y;$z")
}

fun Block.isMarked(): Boolean = pluginData.get<Boolean>("mark.${world.name};$x;$y;$z") == true

fun moveMark(from: Block, to: Block) {
    val oldKey = "mark.${from.world.name};${from.x};${from.y};${from.z}"
    val newKey = "mark.${to.world.name};${to.x};${to.y};${to.z}"

    if (pluginData.get<Boolean>(oldKey) == true) {
        pluginData.remove(oldKey)
        pluginData.set(newKey, true)
    }
}
