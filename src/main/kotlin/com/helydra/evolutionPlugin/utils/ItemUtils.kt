package com.helydra.evolutionPlugin.utils

import com.helydra.evolutionPlugin.plugin
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataType

fun ItemMeta.hideUselessInfo() {
    addItemFlags(
        ItemFlag.HIDE_PLACED_ON,
        ItemFlag.HIDE_DESTROYS,
        ItemFlag.HIDE_ENCHANTS,
        ItemFlag.HIDE_ATTRIBUTES,
        ItemFlag.HIDE_UNBREAKABLE,
        ItemFlag.HIDE_STORED_ENCHANTS,
        ItemFlag.HIDE_ARMOR_TRIM,
        ItemFlag.HIDE_DYE,
        ItemFlag.HIDE_ADDITIONAL_TOOLTIP
    )
}

val emptyItem = ItemStack(Material.GRAY_STAINED_GLASS_PANE).apply {
    val meta = itemMeta
    meta.isHideTooltip = true
    itemMeta = meta
}

fun playerHead(player: Player, displayName: Component): ItemStack {
    val item = ItemStack(Material.PLAYER_HEAD)
    val meta = item.itemMeta as? SkullMeta ?: return ItemStack(Material.AIR)

    // Use the player's real profile (Paper API)
    meta.playerProfile = player.playerProfile

    // Set custom name
    meta.displayName(displayName)

    item.itemMeta = meta
    return item
}

fun ItemMeta.disableCrafting() {
    persistentDataContainer.set(NamespacedKey(plugin, "prevent_from_crafting"), PersistentDataType.BOOLEAN, true)
}

fun ItemStack?.canCraft(): Boolean {
    if (this == null) return true
    val meta = itemMeta ?: return true
    return meta.persistentDataContainer.get(NamespacedKey(plugin, "prevent_from_crafting"), PersistentDataType.BOOLEAN) == null
}

fun Player.giveOrDrop(itemStack: ItemStack) {
    for (stack in give(itemStack).leftovers()) {
        world.dropItem(location, stack)
    }
}