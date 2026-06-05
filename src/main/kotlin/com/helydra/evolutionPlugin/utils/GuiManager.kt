package com.helydra.evolutionPlugin.utils

import com.helydra.evolutionPlugin.plugin
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

val openedInventories = mutableMapOf<Player, Gui>()

class ItemSlot(
    val item: ItemStack,
    val onClick: (ClickType) -> Unit
)

class Gui(
    val name: Component,
    val rows: Int,
    val slots: Map<Int, ItemSlot>
)

fun Player.openGui(gui: Gui) {
    val name = gui.name
    val slots = gui.slots
    val inventory = Bukkit.createInventory(null, gui.rows * 9, name)
    for ((slot, itemSlot) in slots) {
        inventory.setItem(slot, itemSlot.item)
    }
    openInventory(inventory)
    Bukkit.getScheduler().runTask(plugin, Runnable {
        openedInventories[this] = gui
    })
}