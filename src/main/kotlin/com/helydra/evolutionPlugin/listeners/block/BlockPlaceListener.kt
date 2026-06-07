package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class BlockPlaceListener : Listener {

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player
        val block = event.block

        block.mark(true)
    }

}