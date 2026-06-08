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

class WoodcuttingSkill : Skill {
    override val name = "Woodcutting"
    override val id = "woodcutting"
    override val materialIcon = Material.DIAMOND_AXE
    override val description = listOf(mm("<gray>Cut trees"))
    override val attributes = listOf(
        BlockRangeWoodcuttingSkillAttribute(this),
        WoodcuttingExperienceWoodcuttingSkillAttribute(this)
    )
    override val spells = listOf<SkillSpell>()
}

class BlockRangeWoodcuttingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Block range"
    override val id = "block_range"
    override val materialIcon = Material.LAPIS_LAZULI
    override val description = listOf(mm("<gray>Increases your block range"))
    override val maxLevel = 5
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.BLOCK_INTERACTION_RANGE, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 1.0 }
    }
}

class WoodcuttingExperienceWoodcuttingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Woodcutting experience"
    override val id = "woodcutting_experience"
    override val materialIcon = Material.EXPERIENCE_BOTTLE
    override val description = listOf(mm("<gray>Gives you exp by chopping trees"))
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.WOODCUTTING_EXPERIENCE, this)
    }
}