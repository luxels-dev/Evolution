package com.helydra.evolutionPlugin.listeners

import com.helydra.evolutionPlugin.managers.openGui
import com.helydra.evolutionPlugin.managers.openedInventories
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
        val itemSlot = gui.slots[slot]
        val clickType = event.click
        itemSlot?.onClick?.invoke(clickType)
        for (pageInstance in gui.pageInstances) {
            if (pageInstance.rightPage != null && slot == pageInstance.rightPage.first) {
                pageInstance.page++
                player.openGui(gui)
            }
            if (pageInstance.leftPage != null && slot == pageInstance.leftPage.first) {
                pageInstance.page--
                player.openGui(gui)
            }
            for ((index, insideSlot) in pageInstance.slots.withIndex()) {
                if (insideSlot != slot) continue
                val itemIndex = (pageInstance.page - 1) * pageInstance.slots.size + index
                val itemSlot = pageInstance.items.getOrNull(itemIndex) ?: continue
                itemSlot.onClick.invoke(clickType)
                break
            }
        }
    }

}