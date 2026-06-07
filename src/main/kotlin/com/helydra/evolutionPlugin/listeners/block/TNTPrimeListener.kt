package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.utils.mark
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.TNTPrimeEvent

class TNTPrimeListener : Listener {

    @EventHandler
    fun onTNTPrime(event: TNTPrimeEvent) {
        val block = event.block

        block.mark(false)
    }

}