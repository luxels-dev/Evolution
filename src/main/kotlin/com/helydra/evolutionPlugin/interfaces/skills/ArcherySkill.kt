package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class ArcherySkill : Skill {
    override val name = "Archery"
    override val id = "archery"
    override val materialIcon = Material.BOW
    override val description = listOf(mm("<gray>Shoot with a bow or a crossbow"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}