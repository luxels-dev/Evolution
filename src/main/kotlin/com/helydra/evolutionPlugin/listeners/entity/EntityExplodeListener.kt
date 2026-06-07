package com.helydra.evolutionPlugin.listeners.entity

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.entity.Creeper
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent

class EntityExplodeListener : Listener {

    @EventHandler
    fun onEntityExplode(event: EntityExplodeEvent) {
        val entity = event.entity
        if (entity is Creeper) event.blockList().clear()

        for (block in event.blockList()) {
            block.mark(false)
        }
    }

}