package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.checkCustomAttributes
import com.helydra.evolutionPlugin.utils.mm
import com.helydra.evolutionPlugin.utils.skillAttributeCheck
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player

class DefenceSkill : Skill {
    override val name = "Defence"
    override val id = "defence"
    override val materialIcon = Material.DIAMOND_CHESTPLATE
    override val description = listOf(mm("<gray>Take damage"))
    override val attributes = listOf(
        ExplosionKnockbackResistanceDefenceSkillAttribute(this),
        KnockbackResistanceDefenceSkillAttribute(this),
        HealthDefenceSkillAttribute(this),
        RegenerationDefenceSkillAttribute(this),
    )
    override val spells = listOf<SkillSpell>()
}

class ExplosionKnockbackResistanceDefenceSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Explosion knockback resistance"
    override val id = "explosion_knockback_resistance"
    override val materialIcon = Material.GUNPOWDER
    override val description = listOf(mm("<gray>Increases your explosion knockback resistance"))
    override val maxLevel = 5
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.EXPLOSION_KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.2 }
    }
}

class KnockbackResistanceDefenceSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Knockback resistance"
    override val id = "knockback_resistance"
    override val materialIcon = Material.SUGAR
    override val description = listOf(mm("<gray>Increases your knockback resistance"))
    override val maxLevel = 5
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.KNOCKBACK_RESISTANCE, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.2 }
    }
}

class HealthDefenceSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Health"
    override val id = "health"
    override val materialIcon = Material.REDSTONE
    override val description = listOf(mm("<gray>Increases your max health"))
    override val maxLevel = 20
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.MAX_HEALTH, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 2.0 }
    }
}

class RegenerationDefenceSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Regeneration"
    override val id = "regeneration"
    override val materialIcon = Material.GOLDEN_APPLE
    override val description = listOf(mm("<gray>Enables you to regenerate naturally"))
    override val maxLevel = 5
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.REGENERATION, this)
    }
}