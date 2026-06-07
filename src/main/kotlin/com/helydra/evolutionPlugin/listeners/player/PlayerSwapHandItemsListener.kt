package com.helydra.evolutionPlugin.listeners.player

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerSwapHandItemsEvent

class PlayerSwapHandItemsListener : Listener {

    @EventHandler
    fun onPlayerSwapHandItemsEvent(event: PlayerSwapHandItemsEvent) {
        val player = event.player
        if (player.isSneaking) return
        event.isCancelled = true

    }

}