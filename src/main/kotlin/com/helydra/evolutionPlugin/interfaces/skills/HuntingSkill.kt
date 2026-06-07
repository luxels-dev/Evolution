package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.plugin
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player

class HuntingSkill : Skill {
    override val name = "Hunting"
    override val id = "hunting"
    override val materialIcon = Material.DIAMOND_SWORD
    override val description = listOf(mm("<gray>Kill mobs or animals"))
    override val attributes = listOf(
        StrengthHuntingSkillAttribute(this),
        AttackSpeedHuntingSkillAttribute(this),
        AttackKnockbackHuntingSkillAttribute(this),
        SlayingExperienceHuntingSkillAttribute(this)
    )
    override val spells = listOf<SkillSpell>()
}

class StrengthHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Strength"
    override val id = "strength"
    override val materialIcon = Material.REDSTONE
    override val description = listOf(mm("<gray>Increases the damage you deal"))
    override val maxLevel = 10
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        val key = NamespacedKey(plugin, "attribute_$id")
        player.getAttribute(Attribute.ATTACK_DAMAGE)?.removeModifier(key)
        player.getAttribute(Attribute.ATTACK_DAMAGE)?.addModifier(AttributeModifier(key, 1.0, AttributeModifier.Operation.ADD_NUMBER))
    }
}

class AttackSpeedHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Attack Speed"
    override val id = "attack_speed"
    override val materialIcon = Material.SUGAR
    override val description = listOf(mm("<gray>Increases your attack speed"))
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        val key = NamespacedKey(plugin, "attribute_$id")
        player.getAttribute(Attribute.ATTACK_SPEED)?.removeModifier(key)
        player.getAttribute(Attribute.ATTACK_SPEED)?.addModifier(AttributeModifier(key, 1.0, AttributeModifier.Operation.ADD_NUMBER))
    }
}

class AttackKnockbackHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Attack Knockback"
    override val id = "attack_knockback"
    override val materialIcon = Material.GUNPOWDER
    override val description = listOf(mm("<gray>Increases your attack knockback"))
    override val maxLevel = 8
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        val key = NamespacedKey(plugin, "attribute_$id")
        player.getAttribute(Attribute.ATTACK_KNOCKBACK)?.removeModifier(key)
        player.getAttribute(Attribute.ATTACK_KNOCKBACK)?.addModifier(AttributeModifier(key, 0.5, AttributeModifier.Operation.ADD_NUMBER))
    }
}

val slayingExperienceAttributeMap = mutableMapOf<Player, Int>()

class SlayingExperienceHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Slaying Experience"
    override val id = "slaying_experience"
    override val materialIcon = Material.EXPERIENCE_BOTTLE
    override val description = listOf(
        mm("<gray>Increases the amount of experience"),
        mm("<gray>gained by slaying mobs")
    )
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        slayingExperienceAttributeMap.remove(player)
        val level = level(player) ?: return
        val enabled = isEnabled(player) ?: return
        if (level > 0 && enabled) slayingExperienceAttributeMap[player] = level
    }
}