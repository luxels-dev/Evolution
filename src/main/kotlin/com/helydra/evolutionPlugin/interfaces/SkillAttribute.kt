package com.helydra.evolutionPlugin.interfaces

import com.helydra.evolutionPlugin.data
import com.helydra.evolutionPlugin.utils.hideUselessInfo
import com.helydra.evolutionPlugin.utils.mm
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface SkillAttribute {
    val skill: Skill
    val name: String
    val id: String
    val materialIcon: Material
    val description: List<Component>
    val maxLevel: Int
    val pointsPerLevel: Int

    fun check(player: Player)

    fun icon(player: Player) = ItemStack(materialIcon).apply {
        val meta = itemMeta
        val level = level(player) ?: 0
        meta.itemName(mm("<color:#55919D>Attribute : <color:#B0E7F3>$name"))
        val lore = mutableListOf(
            mm("<color:#55919D> ● Level : <color:#B0E7F3>$level<color:#55919D>/$maxLevel")
        )
        if (level < maxLevel) lore.addLast(mm("<color:#55919D> ● Cost : <color:#B0E7F3>$pointsPerLevel<color:#55919D> points"))
        val enabled = isEnabled(player) ?: false
        if (level > 0) lore.addLast(mm("<color:#55919D> ● Enabled : ${if (enabled) "<green>Yes" else "<red>No"}"))
        lore.addAll(description)
        if (level < maxLevel) lore.addLast(mm("<dark_gray>Left click to buy"))
        if (level > 0) lore.addLast(mm("<dark_gray>Right click to toggle"))
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

    fun isEnabled(player: Player): Boolean? = data.get<Boolean>("skill_attribute.$id.enabled.${player.uniqueId}")
    fun setEnabled(player: Player, enabled: Boolean) = data.set("skill_attribute.$id.enabled.${player.uniqueId}", enabled)
    fun removeEnabled(player: Player) = data.remove("skill_attribute.$id.enabled.${player.uniqueId}")
    fun toggleEnabled(player: Player) {
        val enabled = isEnabled(player) ?: return
        if (enabled) setEnabled(player, false) else setEnabled(player, true)
        check(player)
    }

    fun buyAttempt(player: Player): Boolean {
        val points = skill.points(player) ?: return false
        if (points < pointsPerLevel) return false
        val level = level(player) ?: return false
        if (level >= maxLevel) return false

        levelUp(player)
        skill.addPoints(player, -pointsPerLevel)
        setEnabled(player, true)
        check(player)
        return true
    }

    fun enableAttempt(player: Player) {
        val level = level(player) ?: return
        if (level > 0) {
            toggleEnabled(player)
        }
    }

    fun verify(player: Player) {
        val level = level(player)
        val enabled = isEnabled(player)

        if (level == null || level < 0) setLevel(player, 0)
        if (enabled == null) setEnabled(player, false)
    }

    fun reset(player: Player) {
        removeLevel(player)
        removeEnabled(player)
        verify(player)
    }

}