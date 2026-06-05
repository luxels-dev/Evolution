package com.helydra.evolutionPlugin.data

import org.bukkit.Bukkit
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class DataManager(private val plugin: JavaPlugin) {

    private val file = File(plugin.dataFolder, "data.yml")
    var config: FileConfiguration = YamlConfiguration()
    val lock = ReentrantLock()

    fun load() {
        if (!file.exists()) {
            plugin.saveResource("data.yml", false)
        }
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun reload() = lock.withLock {
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun save() = lock.withLock {
        config.save(file)
    }

    fun set(path: String, value: Any?) = lock.withLock {
        config.set(path, value)
        Bukkit.getScheduler().runTaskAsynchronously(plugin, Runnable {
            save()
        })
    }

    inline fun <reified T> get(path: String): T? = lock.withLock {
        val value = config.get(path) ?: return null
        value as? T
    }

    fun remove(path: String) = lock.withLock {
        config.set(path, null)
        save()
    }

    fun getSection(path: String): ConfigurationSection? = lock.withLock {
        config.getConfigurationSection(path)
    }

    fun getKeys(path: String, deep: Boolean = false): Set<String> = lock.withLock {
        val section = config.getConfigurationSection(path) ?: return emptySet()
        section.getKeys(deep)
    }

    fun getSections(path: String): List<ConfigurationSection> = lock.withLock {
        val section = config.getConfigurationSection(path) ?: return emptyList()
        section.getKeys(false)
            .mapNotNull { key -> section.getConfigurationSection(key) }
    }

}