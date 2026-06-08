package com.helydra.evolutionPlugin.listeners.inventory

import com.helydra.evolutionPlugin.utils.canCraft
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.PrepareItemCraftEvent

class PrepareItemCraftListener : Listener {

    @EventHandler
    fun onPrepareItemCraft(event: PrepareItemCraftEvent) {
        for (itemStack in event.inventory.matrix) {
            if (!itemStack.canCraft()) {
                event.inventory.result = null
                return
            }
        }
    }

}