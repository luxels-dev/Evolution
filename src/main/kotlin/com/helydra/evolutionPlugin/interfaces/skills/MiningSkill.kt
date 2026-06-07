package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class MiningSkill : Skill {
    override val name = "Mining"
    override val id = "mining"
    override val materialIcon = Material.DIAMOND_PICKAXE
    override val description = listOf(mm("<gray>Mine ores"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}