package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockExplodeEvent

class BlockExplodeListener : Listener {

    @EventHandler
    fun onBlockExplode(event: BlockExplodeEvent) {
        val block = event.block
        block.mark(false)
        for (block in event.blockList()) {
            block.mark(false)
        }
    }

}