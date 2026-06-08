package com.helydra.evolutionPlugin.listeners.inventory

import com.helydra.evolutionPlugin.utils.canCraft
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent

class CraftItemListener : Listener {

    @EventHandler
    fun onCraftItem(event: CraftItemEvent) {
        for (itemStack in event.inventory.matrix) {
            if (!itemStack.canCraft()) {
                event.isCancelled = true
                return
            }
        }
    }

}