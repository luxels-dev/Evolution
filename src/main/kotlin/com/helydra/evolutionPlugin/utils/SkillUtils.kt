package com.helydra.evolutionPlugin.utils

import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.plugin
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
    TREASURE_HUNTER
}

val customAttributeMap = CustomSkillAttribute.entries.associateWith { mutableMapOf<Player, Int>() }

fun checkCustomAttributes(player: Player, attribute: CustomSkillAttribute, skillAttribute: SkillAttribute) {
    customAttributeMap[attribute]?.remove(player) ?: return
    val level = skillAttribute.level(player) ?: 0
    val enabled = skillAttribute.isEnabled(player) ?: false
    if (level > 0 && enabled) customAttributeMap[attribute]?.set(player, level)
}

fun attributeLevel(player: Player, attribute: CustomSkillAttribute): Int = customAttributeMap[attribute]?.get(player) ?: 0