package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class FarmingSkill : Skill {
    override val name = "Farming"
    override val id = "farming"
    override val materialIcon = Material.DIAMOND_HOE
    override val description = listOf(mm("<gray>Harvest crops"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}