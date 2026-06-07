package com.helydra.evolutionPlugin.listeners.entity

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.entity.FallingBlock
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityChangeBlockEvent

class EntityChangeBlockListener : Listener {

    @EventHandler
    fun onEntityChangeBlock(event: EntityChangeBlockEvent) {
        val entity = event.entity
        if (entity is FallingBlock) {
            if (markedFallingBlocks.contains(entity)) {
                event.block.mark(true)
                markedFallingBlocks.remove(entity)
            }
        }
    }

}