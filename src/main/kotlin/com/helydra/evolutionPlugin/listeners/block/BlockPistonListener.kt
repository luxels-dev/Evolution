package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.moveMark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPistonExtendEvent
import org.bukkit.event.block.BlockPistonRetractEvent

class BlockPistonListener : Listener {

    @EventHandler
    fun onPistonExtend(event: BlockPistonExtendEvent) {
        val direction = event.direction

        event.blocks
            .sortedByDescending { it.y } // or by distance from piston
            .forEach { block ->
                val newBlock = block.getRelative(direction)
                moveMark(block, newBlock)
            }
    }

    @EventHandler
    fun onPistonRetract(event: BlockPistonRetractEvent) {
        val direction = event.direction

        event.blocks
            .sortedByDescending { it.y }
            .forEach { block ->
                val newBlock = block.getRelative(direction)
                moveMark(block, newBlock)
            }
    }


}