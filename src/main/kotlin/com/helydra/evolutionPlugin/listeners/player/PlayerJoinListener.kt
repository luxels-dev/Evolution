package com.helydra.evolutionPlugin.listeners.player

import com.helydra.evolutionPlugin.managers.SkillManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player
        for (skill in SkillManager().skillList) {
            skill.verify(player)
        }
    }

}