package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBurnEvent

class BlockBurnListener : Listener {

    @EventHandler
    fun onBlockBurn(event: BlockBurnEvent) {
        val block = event.block

        block.mark(false)
    }

}