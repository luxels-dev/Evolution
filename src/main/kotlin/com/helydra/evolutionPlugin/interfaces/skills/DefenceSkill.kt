package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class DefenceSkill : Skill {
    override val name = "Defence"
    override val id = "defence"
    override val materialIcon = Material.DIAMOND_CHESTPLATE
    override val description = listOf(mm("<gray>Take damage"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}