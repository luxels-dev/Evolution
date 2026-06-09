package com.helydra.evolutionPlugin.utils

import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.managers.SkillManager
import com.helydra.evolutionPlugin.plugin
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player

fun skillAttributeCheck(player: Player, skillAttribute: SkillAttribute, attribute: Attribute, operation: AttributeModifier.Operation, amount: (level: Int) -> Double) {
    val key = NamespacedKey(plugin, "attribute_${skillAttribute.id}")
    player.getAttribute(attribute)?.removeModifier(key)
    val level = skillAttribute.level(player) ?: 0
    val enabled = skillAttribute.isEnabled(player) ?: false
    if (enabled && level > 0) {
        player.getAttribute(attribute)
            ?.addModifier(AttributeModifier(key, amount.invoke(level), operation))
    }
}

enum class CustomSkillAttribute {
    WOODCUTTING_EXPERIENCE,
    MINING_EXPERIENCE,
    SLAYING_EXPERIENCE,
    EXCAVATION_EXPERIENCE,
    FARMING_EXPERIENCE,
    FISHING_EXPERIENCE,
    TREASURE_HUNTER,
    REGENERATION,
    ARROW_DAMAGE,
}

fun initRegenLoop() {
    plugin.server.scheduler.runTaskTimer(plugin, Runnable {
        for (player in Bukkit.getOnlinePlayers()) {
            val level = attributeLevel(player, CustomSkillAttribute.REGENERATION)
            if (level > 0) player.heal(level * 0.1)
        }
    }, 0, 20)
}

private val movingMap = mutableMapOf<Player, Location>()

fun initMovingLoop() {
    plugin.server.scheduler.runTaskTimer(plugin, Runnable {
        for (player in Bukkit.getOnlinePlayers()) {
            val oldLoc = movingMap[player]
            if (oldLoc != null) {
                val newLoc = player.location
                if (newLoc.world == oldLoc.world) {
                    val distance = oldLoc.distance(newLoc).toInt()
                    SkillManager().skillFromId("adventuring")?.addExperience(player, distance)
                }
            }
            movingMap[player] = player.location
        }
    }, 0, 20)
}

val customAttributeMap = CustomSkillAttribute.entries.associateWith { mutableMapOf<Player, Int>() }

fun checkCustomAttributes(player: Player, attribute: CustomSkillAttribute, skillAttribute: SkillAttribute) {
    customAttributeMap[attribute]?.remove(player)
    val level = skillAttribute.level(player) ?: 0
    val enabled = skillAttribute.isEnabled(player) ?: false
    if (level > 0 && enabled) customAttributeMap[attribute]?.set(player, level)
}

fun attributeLevel(player: Player, attribute: CustomSkillAttribute) = customAttributeMap[attribute]?.get(player) ?: 0