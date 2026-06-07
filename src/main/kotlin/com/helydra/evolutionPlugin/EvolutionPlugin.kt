package com.helydra.evolutionPlugin

import com.helydra.evolutionPlugin.commands.SkillCommand
import com.helydra.evolutionPlugin.data.DataManager
import com.helydra.evolutionPlugin.listeners.block.*
import com.helydra.evolutionPlugin.listeners.entity.EntityChangeBlockListener
import com.helydra.evolutionPlugin.listeners.entity.EntityDeathListener
import com.helydra.evolutionPlugin.listeners.entity.EntityExplodeListener
import com.helydra.evolutionPlugin.listeners.entity.EntitySpawnListener
import com.helydra.evolutionPlugin.listeners.inventory.InventoryClickListener
import com.helydra.evolutionPlugin.listeners.inventory.InventoryCloseListener
import com.helydra.evolutionPlugin.listeners.player.PlayerJoinListener
import com.helydra.evolutionPlugin.listeners.player.PlayerSwapHandItemsListener
import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: EvolutionPlugin
lateinit var data: DataManager

class EvolutionPlugin : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        plugin = this
        data = DataManager(this)
        data.load()

        getCommand("skill")?.setExecutor(SkillCommand())

        server.pluginManager.registerEvents(InventoryCloseListener(), this)
        server.pluginManager.registerEvents(InventoryClickListener(), this)
        server.pluginManager.registerEvents(PlayerJoinListener(), this)
        server.pluginManager.registerEvents(BlockBreakListener(), this)
        server.pluginManager.registerEvents(BlockBurnListener(), this)
        server.pluginManager.registerEvents(BlockExplodeListener(), this)
        server.pluginManager.registerEvents(BlockFadeListener(), this)
        server.pluginManager.registerEvents(BlockFormToListener(), this)
        server.pluginManager.registerEvents(BlockIgniteListener(), this)
        server.pluginManager.registerEvents(BlockPistonListener(), this)
        server.pluginManager.registerEvents(BlockPlaceListener(), this)
        server.pluginManager.registerEvents(BlockSpreadListener(), this)
        server.pluginManager.registerEvents(EntityChangeBlockListener(), this)
        server.pluginManager.registerEvents(EntityExplodeListener(), this)
        server.pluginManager.registerEvents(EntitySpawnListener(), this)
        server.pluginManager.registerEvents(LeavesDecayListener(), this)
        server.pluginManager.registerEvents(TNTPrimeListener(), this)
        server.pluginManager.registerEvents(EntityDeathListener(), this)
        server.pluginManager.registerEvents(PlayerSwapHandItemsListener(), this)
    }

    override fun onDisable() {

    }

}