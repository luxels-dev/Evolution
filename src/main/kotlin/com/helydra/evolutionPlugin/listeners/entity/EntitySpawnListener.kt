package com.helydra.evolutionPlugin.listeners.entity

import com.helydra.evolutionPlugin.utils.isMarked
import org.bukkit.entity.Entity
import org.bukkit.entity.FallingBlock
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent

val markedFallingBlocks = mutableListOf<Entity>()

class EntitySpawnListener : Listener {

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        val entity = event.entity
        if (entity is FallingBlock) {
            val block = entity.location.block
            if (block.isMarked()) markedFallingBlocks.addLast(entity)
        }
    }

}