package com.helydra.evolutionPlugin

import com.helydra.evolutionPlugin.data.DataManager
import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: EvolutionPlugin
lateinit var data: DataManager

class EvolutionPlugin : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        plugin = this
        data = DataManager(this)
        data.load()
    }

    override fun onDisable() {

    }

}