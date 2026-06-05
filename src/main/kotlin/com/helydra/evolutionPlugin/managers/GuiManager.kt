package com.helydra.evolutionPlugin.managers

import com.helydra.evolutionPlugin.plugin
import com.helydra.evolutionPlugin.utils.mm
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack
import kotlin.collections.iterator
import kotlin.math.ceil

val openedInventories = mutableMapOf<Player, Gui>()

val defaultRightPage = ItemStack(Material.PAPER).apply {
    val meta = itemMeta
    meta.itemName(mm("<gray>Next page"))
    itemMeta = meta
}

val defaultLeftPage = ItemStack(Material.PAPER).apply {
    val meta = itemMeta
    meta.itemName(mm("<gray>Previous page"))
    itemMeta = meta
}

class ItemSlot(
    val item: ItemStack,
    val onClick: (ClickType) -> Unit
)

class Gui(
    val name: Component,
    val rows: Int,
    val slots: Map<Int, ItemSlot>,
    val pageInstances: List<PageInstance> = emptyList()
)

class PageInstance(
    val items: List<ItemSlot>,
    val slots: List<Int>,
    var page: Int,
    val rightPage: Pair<Int, ItemStack>?,
    val leftPage: Pair<Int, ItemStack>?
)

fun Player.openGui(gui: Gui) {
    val name = gui.name
    val slots = gui.slots
    val pageInstances = gui.pageInstances
    val inventory = Bukkit.createInventory(null, gui.rows * 9, name)
    for ((slot, itemSlot) in slots) {
        inventory.setItem(slot, itemSlot.item)
    }
    for (pageInstance in pageInstances) {
        val items = pageInstance.items
        val slots = pageInstance.slots
        var page = pageInstance.page
        val rightPage = pageInstance.rightPage
        val leftPage = pageInstance.leftPage
        val itemSize = items.size
        val slotSize = slots.size
        val pagesNeeded = ceil(itemSize.toDouble() / slotSize).toInt()
        if (page > pagesNeeded) page = pagesNeeded
        if (page < 1) page = 1
        val slotsToBypass = (page - 1) * slotSize
        for ((index, slot) in slots.withIndex()) {
            inventory.setItem(slot, items.getOrNull(slotsToBypass + index)?.item ?: break)
        }
        if (rightPage != null && page < pagesNeeded) inventory.setItem(rightPage.first, rightPage.second)
        if (leftPage != null && page > 1) inventory.setItem(leftPage.first, leftPage.second)
    }
    openInventory(inventory)
    Bukkit.getScheduler().runTask(plugin, Runnable {
        openedInventories[this] = gui
    })
}