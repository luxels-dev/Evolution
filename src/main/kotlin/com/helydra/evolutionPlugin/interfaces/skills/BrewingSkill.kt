package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class BrewingSkill : Skill {
    override val name = "Brewing"
    override val id = "brewing"
    override val materialIcon = Material.BREWING_STAND
    override val description = listOf(mm("<gray>Brew Potions"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}