package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListener : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block
        block.mark(false)
    }

}