package com.helydra.evolutionPlugin.interfaces

import com.helydra.evolutionPlugin.data
import com.helydra.evolutionPlugin.utils.hideUselessInfo
import com.helydra.evolutionPlugin.utils.loadingBar
import com.helydra.evolutionPlugin.utils.mm
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface Skill {

    val name: String
    val id: String
    val materialIcon: Material
    val description: List<Component>
    val attributes: List<SkillAttribute>
    val spells: List<SkillSpell>

    fun icon(player: Player) = ItemStack(materialIcon).apply {
        val meta = itemMeta
        val level = level(player) ?: 1
        val exp = experience(player) ?: 0
        val expUp = experienceUp(player) ?: 1
        val points = points(player) ?: 0
        meta.itemName(mm("<color:#55919D>Skill : <color:#B0E7F3>$name"))
        meta.lore(
            listOf(
                mm("<color:#55919D> ● Level : <color:#B0E7F3>$level"),
                mm("<color:#55919D> ● Exp : ${loadingBar("<color:#B0E7F3>", "<gray>", exp, expUp, 20)} <color:#B0E7F3>$exp<color:#55919D>/$expUp"),
                mm("<color:#55919D> ● Points : <color:#B0E7F3>$points"),
            )
        )
        meta.hideUselessInfo()
        itemMeta = meta
    }

    fun descriptionIcon() = ItemStack(Material.OAK_SIGN).apply {
        val meta = itemMeta
        meta.itemName(mm("<color:#70c6d8>How to level up"))
        meta.lore(description)
        itemMeta = meta
    }

    fun attributesIcon() = ItemStack(Material.DRAGON_BREATH).apply {
        val meta = itemMeta
        meta.itemName(mm("<color:#70c6d8>Attributes"))
        itemMeta = meta
    }

    fun spellsIcon() = ItemStack(Material.NETHER_STAR).apply {
        val meta = itemMeta
        meta.itemName(mm("<color:#70c6d8>Spells"))
        itemMeta = meta
    }

    fun level(player: Player): Int? = data.get<Int>("skill.$id.level.${player.uniqueId}")
    fun setLevel(player: Player, level: Int) = data.set("skill.$id.level.${player.uniqueId}", level)
    fun removeLevel(player: Player) = data.remove("skill.$id.level.${player.uniqueId}")
    fun levelUp(player: Player) {
        val level = level(player) ?: return
        setLevel(player, level + 1)
    }

    fun experience(player: Player): Int? = data.get<Int>("skill.$id.experience.${player.uniqueId}")
    fun setExperience(player: Player, experience: Int) = data.set("skill.$id.experience.${player.uniqueId}", experience)
    fun removeExperience(player: Player) = data.remove("skill.$id.experience.${player.uniqueId}")
    fun addExperience(player: Player, experience: Int) {
        secureAddExperience(player, experience)
        levelingAttempt(player)
    }
    fun secureAddExperience(player: Player, experience: Int) {
        val beforeExperience = experience(player) ?: return
        setExperience(player, beforeExperience + experience)
    }

    fun experienceUp(level: Int) = (500.0 + 35.0 * (level-1) + 20 * ((level-1) + (kotlin.math.exp(-0.5 * (level-1)) - 1.0) / 0.5)).toInt()
    fun experienceUp(player: Player) = level(player)?.let { experienceUp(it) }

    fun points(player: Player): Int? = data.get<Int>("skill.$id.points.${player.uniqueId}")
    fun setPoints(player: Player, points: Int) = data.set("skill.$id.points.${player.uniqueId}", points)
    fun removePoints(player: Player) = data.remove("skill.$id.points.${player.uniqueId}")
    fun addPoints(player: Player, points: Int) {
        val beforePoints = points(player) ?: return
        setPoints(player, beforePoints + points)
    }

    fun levelingAttempt(player: Player): Boolean {
        var didEdit = false
        val beforeLevel = level(player) ?: return false
        while ((experience(player) ?: return false) >= (experienceUp(player) ?: return false)) {
            secureAddExperience(player, -(experienceUp(player) ?: return false))
            levelUp(player)

            val level = level(player) ?: return false
            var pointsToAdd = 1
            if (level % 5 == 0) pointsToAdd++
            if (level % 20 == 0) pointsToAdd += 2
            if (level % 100 == 0) pointsToAdd += 5
            addPoints(player, pointsToAdd)
            didEdit = true
        }
        val newLevel = level(player) ?: return false
        if (beforeLevel < newLevel) player.sendMessage(mm("<color:#C2E4FF>You leveled up your <color:#C2FFFC>$name<color:#C2E4FF> skill (<color:#C2C5FF>$beforeLevel<color:#C2E4FF> -> <color:#C2C5FF>$newLevel<color:#C2E4FF>)"))
        return didEdit
    }

    fun verify(player: Player) {
        val experience = experience(player)
        val level = level(player)
        val points = points(player)

        if (experience == null || experience < 0) setExperience(player, 0)
        if (level == null || level < 1) setLevel(player, 1)
        if (points == null || points < 0) setPoints(player, 0)
    }

    fun reset(player: Player) {
        removeLevel(player)
        removeExperience(player)
        removePoints(player)
        verify(player)
    }

}