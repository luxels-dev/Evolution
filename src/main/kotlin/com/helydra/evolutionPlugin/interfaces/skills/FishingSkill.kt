package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class FishingSkill : Skill {
    override val name = "Fishing"
    override val id = "fishing"
    override val materialIcon = Material.FISHING_ROD
    override val description = listOf(mm("<gray>Fish with a fishing rod"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}