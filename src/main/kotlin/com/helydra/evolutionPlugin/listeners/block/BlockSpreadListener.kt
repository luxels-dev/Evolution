package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockSpreadEvent

class BlockSpreadListener : Listener {

    @EventHandler
    fun onBlockSpread(event: BlockSpreadEvent) {
        val block = event.block

        block.mark(false)
    }

}