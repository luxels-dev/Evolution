package com.helydra.evolutionPlugin

import com.helydra.evolutionPlugin.commands.CustomGiveCommand
import com.helydra.evolutionPlugin.commands.SkillCommand
import com.helydra.evolutionPlugin.commands.SpellCommand
import com.helydra.evolutionPlugin.data.DataManager
import com.helydra.evolutionPlugin.listeners.block.*
import com.helydra.evolutionPlugin.listeners.entity.EntityChangeBlockListener
import com.helydra.evolutionPlugin.listeners.entity.EntityDamageListener
import com.helydra.evolutionPlugin.listeners.entity.EntityDeathListener
import com.helydra.evolutionPlugin.listeners.entity.EntityExplodeListener
import com.helydra.evolutionPlugin.listeners.entity.EntitySpawnListener
import com.helydra.evolutionPlugin.listeners.inventory.CraftItemListener
import com.helydra.evolutionPlugin.listeners.inventory.InventoryClickListener
import com.helydra.evolutionPlugin.listeners.inventory.InventoryCloseListener
import com.helydra.evolutionPlugin.listeners.inventory.PrepareItemCraftListener
import com.helydra.evolutionPlugin.listeners.player.PlayerInteractListener
import com.helydra.evolutionPlugin.listeners.player.PlayerJoinListener
import com.helydra.evolutionPlugin.listeners.player.PlayerSwapHandItemsListener
import com.helydra.evolutionPlugin.listeners.player.PlayerToggleSneakListener
import com.helydra.evolutionPlugin.managers.SpellManager
import com.helydra.evolutionPlugin.utils.initMovingLoop
import com.helydra.evolutionPlugin.utils.initRegenLoop
import org.bukkit.plugin.java.JavaPlugin

lateinit var plugin: EvolutionPlugin
lateinit var data: DataManager
lateinit var spellManager: SpellManager

class EvolutionPlugin : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        plugin = this
        data = DataManager(this)
        data.load()

        spellManager = SpellManager()

        getCommand("skill")?.setExecutor(SkillCommand())
        getCommand("spell")?.setExecutor(SpellCommand())
        getCommand("customgive")?.setExecutor(CustomGiveCommand())

        getCommand("customgive")?.tabCompleter = CustomGiveCommand()

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
        server.pluginManager.registerEvents(CraftItemListener(), this)
        server.pluginManager.registerEvents(PrepareItemCraftListener(), this)
        server.pluginManager.registerEvents(PlayerInteractListener(), this)
        server.pluginManager.registerEvents(PlayerToggleSneakListener(), this)
        server.pluginManager.registerEvents(PlayerHarvestBlockListener(), this)
        server.pluginManager.registerEvents(EntityDamageListener(), this)

        initRegenLoop()
        initMovingLoop()
    }

    override fun onDisable() {

    }

}