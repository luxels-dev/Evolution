package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFadeEvent

class BlockFadeListener : Listener {

    @EventHandler
    fun onBlockFade(event: BlockFadeEvent) {
        val block = event.block

        if (event.newState.type != block.type) block.mark(false)
    }

}