package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class EnchantingSkill : Skill {
    override val name = "Enchanting"
    override val id = "enchanting"
    override val materialIcon = Material.ENCHANTING_TABLE
    override val description = listOf(mm("<gray>Enchant stuff"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}