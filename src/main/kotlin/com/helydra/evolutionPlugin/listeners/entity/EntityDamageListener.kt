package com.helydra.evolutionPlugin.listeners.entity

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class EntityDamageListener : Listener {

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        val entity = event.entity
    }

}