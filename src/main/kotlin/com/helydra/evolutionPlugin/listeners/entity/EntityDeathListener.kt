package com.helydra.evolutionPlugin.listeners.entity

import com.helydra.evolutionPlugin.interfaces.skills.slayingExperienceAttributeMap
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityDeathListener : Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity
        val attacker = event.damageSource.causingEntity ?: return
        if (attacker !is Player) return
        val expBoost = slayingExperienceAttributeMap[attacker]
        if (expBoost != null) {
            event.droppedExp += expBoost
        }
    }

}