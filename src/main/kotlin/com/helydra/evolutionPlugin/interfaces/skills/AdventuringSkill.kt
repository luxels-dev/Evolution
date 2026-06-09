package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.mm
import com.helydra.evolutionPlugin.utils.skillAttributeCheck
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player

class AdventuringSkill : Skill {
    override val name = "Adventuring"
    override val id = "adventuring"
    override val materialIcon = Material.COMPASS
    override val description = listOf(mm("<gray>Walk, run, swim or glide"))
    override val attributes = listOf(
        SpeedAdventuringSkillAttribute(this),
        FallResistanceAdventuringSkillAttribute(this),
        MovementEfficiencyAdventuringSkillAttribute(this),
        WaterMovementEfficiencyAdventuringSkillAttribute(this),
        JumpBoostAdventuringSkillAttribute(this),
    )
    override val spells = listOf<SkillSpell>()
}

class SpeedAdventuringSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Speed"
    override val id = "speed"
    override val materialIcon = Material.WIND_CHARGE
    override val description = listOf(mm("<gray>Increases your speed"))
    override val maxLevel = 20
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.MOVEMENT_SPEED, AttributeModifier.Operation.MULTIPLY_SCALAR_1) { level -> level * 0.05 + 1 }
    }
}

class FallResistanceAdventuringSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Fall Resistance"
    override val id = "fall_resistance"
    override val materialIcon = Material.FEATHER
    override val description = listOf(mm("<gray>Increases your fall resistance"))
    override val maxLevel = 20
    override val pointsPerLevel = 3
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.SAFE_FALL_DISTANCE, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.5 }
    }
}

class MovementEfficiencyAdventuringSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Movement Efficiency"
    override val id = "movement_efficiency"
    override val materialIcon = Material.BRICK
    override val description = listOf(mm("<gray>Decreases the slowness caused by special grounds"))
    override val maxLevel = 5
    override val pointsPerLevel = 5
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.MOVEMENT_EFFICIENCY, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.2 }
    }
}

class WaterMovementEfficiencyAdventuringSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Water Movement Efficiency"
    override val id = "water_movement_efficiency"
    override val materialIcon = Material.WATER_BUCKET
    override val description = listOf(mm("<gray>Decreases the slowness caused by water"))
    override val maxLevel = 5
    override val pointsPerLevel = 5
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.WATER_MOVEMENT_EFFICIENCY, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.2 }
    }
}

class JumpBoostAdventuringSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Jump Boost"
    override val id = "jump_boost"
    override val materialIcon = Material.RABBIT_FOOT
    override val description = listOf(mm("<gray>Increases your jump strength"))
    override val maxLevel = 4
    override val pointsPerLevel = 5
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.JUMP_STRENGTH, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.02 }
    }
}