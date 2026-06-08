package com.helydra.evolutionPlugin.listeners.player

import com.helydra.evolutionPlugin.plugin
import com.helydra.evolutionPlugin.spellManager
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType

class PlayerInteractListener : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val item = event.item ?: return
        val meta = item.itemMeta ?: return
        if (meta.persistentDataContainer.get(NamespacedKey(plugin, "custom_item"), PersistentDataType.STRING) == "magic_wand") {
            event.isCancelled = true
            spellManager.handleWandClick(player, event.action)
        }
    }

}