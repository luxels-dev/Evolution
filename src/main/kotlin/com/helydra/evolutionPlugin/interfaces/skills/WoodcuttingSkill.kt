package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class WoodcuttingSkill : Skill {
    override val name = "Woodcutting"
    override val id = "woodcutting"
    override val materialIcon = Material.DIAMOND_AXE
    override val description = listOf(mm("<gray>Cut trees"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}