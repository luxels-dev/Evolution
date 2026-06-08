package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.interfaces.skills.addFarmingExpFromBlock
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerHarvestBlockEvent

class PlayerHarvestBlockListener : Listener {

    @EventHandler
    fun onPlayerHarvestBlock(event: PlayerHarvestBlockEvent) {
        val player = event.player
        val block = event.harvestedBlock

        addFarmingExpFromBlock(player, block)
    }

}