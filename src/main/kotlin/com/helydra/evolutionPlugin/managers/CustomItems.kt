package com.helydra.evolutionPlugin.managers

import com.helydra.evolutionPlugin.plugin
import com.helydra.evolutionPlugin.utils.disableCrafting
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

val magicWand = ItemStack(Material.PAPER).apply {
    val meta = itemMeta
    meta.itemName(mm("Magic Wand"))
    meta.itemModel = Material.BREEZE_ROD.key
    meta.persistentDataContainer.set(NamespacedKey(plugin, "custom_item"), PersistentDataType.STRING, "magic_wand")
    meta.disableCrafting()
    itemMeta = meta
}