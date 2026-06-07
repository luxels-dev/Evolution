package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockIgniteEvent

class BlockIgniteListener : Listener {

    @EventHandler
    fun onBlockIgnite(event: BlockIgniteEvent) {
        val block = event.block

        block.mark(false)
    }

}