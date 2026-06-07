package com.helydra.evolutionPlugin.interfaces

import com.helydra.evolutionPlugin.data
import com.helydra.evolutionPlugin.utils.hideUselessInfo
import com.helydra.evolutionPlugin.utils.mm
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface SkillSpell {
    val skill: Skill
    val name: String
    val id: String
    val materialIcon: Material
    val description: List<Component>
    val maxLevel: Int
    val pointsPerLevel: Int
    val manaCostPerLevel: Int

    fun cast(player: Player)

    fun icon(player: Player) = ItemStack(materialIcon).apply {
        val meta = itemMeta
        val level = level(player) ?: 0
        meta.itemName(mm("<color:#55919D>Spell : <color:#B0E7F3>$name"))
        val lore = mutableListOf(
            mm("<color:#55919D> ● Level : <color:#B0E7F3>$level<color:#55919D>/$maxLevel")
        )
        if (level < maxLevel) lore.addLast(mm("<color:#55919D> ● Cost : <color:#B0E7F3>$pointsPerLevel<color:#55919D> points"))
        if (level > 0) lore.addLast(mm("<color:#55919D> ● Mana cost : <color:#B0E7F3>${manaCostPerLevel * level}<color:#55919D>"))
        lore.addAll(description)
        if (level < maxLevel) lore.addLast(mm("<dark_gray>Left click to buy"))
        meta.lore(lore)
        meta.hideUselessInfo()
        itemMeta = meta
    }

    fun level(player: Player): Int? = data.get<Int>("skill.$id.level.${player.uniqueId}")
    fun setLevel(player: Player, level: Int) = data.set("skill.$id.level.${player.uniqueId}", level)
    fun removeLevel(player: Player) = data.remove("skill.$id.level.${player.uniqueId}")
    fun levelUp(player: Player) {
        val level = level(player) ?: return
        setLevel(player, level + 1)
    }

    fun buyAttempt(player: Player): Boolean {
        val points = skill.points(player) ?: return false
        if (points < pointsPerLevel) return false
        val level = level(player) ?: return false
        if (level >= maxLevel) return false

        levelUp(player)
        skill.addPoints(player, -pointsPerLevel)
        return true
    }

    fun verify(player: Player) {
        val level = level(player)

        if (level == null || level < 0) setLevel(player, 0)
    }

    fun reset(player: Player) {
        removeLevel(player)
        verify(player)
    }

}