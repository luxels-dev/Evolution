package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class TradingSkill : Skill {
    override val name = "Trading"
    override val id = "Trading"
    override val materialIcon = Material.EMERALD
    override val description = listOf(mm("<gray>Trade with villagers"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}