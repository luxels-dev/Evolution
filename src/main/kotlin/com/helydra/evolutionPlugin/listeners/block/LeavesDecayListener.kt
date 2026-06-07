package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.LeavesDecayEvent

class LeavesDecayListener : Listener {

    @EventHandler
    fun onLeavesDecay(event: LeavesDecayEvent) {
        val block = event.block

        block.mark(false)
    }

}