package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class ExcavationSkill : Skill {
    override val name = "Excavation"
    override val id = "excavation"
    override val materialIcon = Material.DIAMOND_SHOVEL
    override val description = listOf(
        mm("<gray>Break blocks that are breakable"),
        mm("<gray>with a shovel"),
    )
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}