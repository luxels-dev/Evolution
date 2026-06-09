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

class FishingSkill : Skill {
    override val name = "Fishing"
    override val id = "fishing"
    override val materialIcon = Material.FISHING_ROD
    override val description = listOf(mm("<gray>Fish with a fishing rod"))
    override val attributes = listOf(
        LuckFishingSkillAttribute(this),
        FishingExperienceFishingSkillAttribute(this),
    )
    override val spells = listOf<SkillSpell>()
}

class LuckFishingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Luck"
    override val id = "luck"
    override val materialIcon = Material.EMERALD
    override val description = listOf(mm("<gray>Increases your luck"))
    override val maxLevel = 25
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.LUCK, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 2.0 }
    }
}

class FishingExperienceFishingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Fishing Experience"
    override val id = "fishing_experience"
    override val materialIcon = Material.EXPERIENCE_BOTTLE
    override val description = listOf(
        mm("<gray>Increases the amount of experience"),
        mm("<gray>gained by fishing")
    )
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.FISHING_EXPERIENCE, this)
    }
}