package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material

class CareTakingSkill : Skill {
    override val name = "Care Taking"
    override val id = "care_taking"
    override val materialIcon = Material.SHEARS
    override val description = listOf(mm("<gray>Breed or tame animals, shear sheep, milk cows"))
    override val attributes = listOf<SkillAttribute>()
    override val spells = listOf<SkillSpell>()
}