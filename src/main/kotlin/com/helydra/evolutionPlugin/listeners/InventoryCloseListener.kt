package com.helydra.evolutionPlugin.listeners

import com.helydra.evolutionPlugin.utils.openedInventories
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent

class InventoryCloseListener : Listener {

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val player = event.player as? Player ?: return
        openedInventories.remove(player)
    }

}