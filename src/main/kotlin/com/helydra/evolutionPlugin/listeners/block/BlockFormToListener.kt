package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFromToEvent

class BlockFormToListener : Listener {

    @EventHandler
    fun onBlockFormTo(event: BlockFromToEvent) {
        val source = event.block
        val destination = event.toBlock

        source.mark(false)
        destination.mark(false)
    }

}