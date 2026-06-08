package com.helydra.evolutionPlugin.listeners.player

import com.helydra.evolutionPlugin.spellManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerToggleSneakEvent

class PlayerToggleSneakListener : Listener {

    @EventHandler
    fun onPlayerToggleSneak(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (!event.isSneaking) return
        spellManager.comboMap[player]?.clear()
        player.clearTitle()
    }

}