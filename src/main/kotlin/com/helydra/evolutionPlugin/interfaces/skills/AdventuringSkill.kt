package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class AdventuringSkill : Skill {
    override val name = "Adventuring"
    override val id = "adventuring"
    override val materialIcon = Material.COMPASS
    override val description = listOf(mm("<gray>Walk, run, swim or glide"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}