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

class ArcherySkill : Skill {
    override val name = "Archery"
    override val id = "archery"
    override val materialIcon = Material.BOW
    override val description = listOf(mm("<gray>Shoot with a bow or a crossbow"))
    override val attributes = listOf(
        ArrowDamageArcherySkillAttribute(this)
    )
    override val spells = listOf<SkillSpell>()
}

class ArrowDamageArcherySkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Arrow damage"
    override val id = "arrow_damage"
    override val materialIcon = Material.REDSTONE
    override val description = listOf(mm("<gray>Increases the damage dealt by your arrows"))
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.ARROW_DAMAGE, this)
    }
}