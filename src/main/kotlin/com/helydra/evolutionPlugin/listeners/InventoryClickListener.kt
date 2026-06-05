package com.helydra.evolutionPlugin.listeners

import com.helydra.evolutionPlugin.utils.openedInventories
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class InventoryClickListener : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        val gui = openedInventories[player] ?: return
        val slot = event.rawSlot
        event.isCancelled = true
        val itemSlot = gui.slots[slot] ?: return
        val clickType = event.click
        itemSlot.onClick.invoke(clickType)
    }

}