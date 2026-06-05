package com.helydra.evolutionPlugin

import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: EvolutionPlugin

class EvolutionPlugin : JavaPlugin() {

    override fun onEnable() {
        plugin = this
    }

    override fun onDisable() {

    }

}