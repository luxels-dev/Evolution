package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.checkCustomAttributes
import com.helydra.evolutionPlugin.utils.mm
import com.helydra.evolutionPlugin.utils.skillAttributeCheck
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.Player

class MiningSkill : Skill {
    override val name = "Mining"
    override val id = "mining"
    override val materialIcon = Material.DIAMOND_PICKAXE
    override val description = listOf(mm("<gray>Mine ores"))
    override val attributes = listOf(
        MiningSpeedMiningSkillAttribute(this),
        BlockBreakingSpeedMiningSkillAttribute(this),
        UnderWaterMiningSpeedMiningSkillAttribute(this),
        MiningExperienceMiningSkillAttribute(this)
    )
    override val spells = listOf<SkillSpell>()
}

class MiningSpeedMiningSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Mining speed"
    override val id = "mining_speed"
    override val materialIcon = Material.SUGAR
    override val description = listOf(mm("<gray>Increases your mining efficiency (With a tool)"))
    override val maxLevel = 15
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.MINING_EFFICIENCY, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 2.0 }
    }
}

class UnderWaterMiningSpeedMiningSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Underwater mining speed"
    override val id = "underwater_mining_speed"
    override val materialIcon = Material.WATER_BUCKET
    override val description = listOf(mm("<gray>Increases your mining speed under water"))
    override val maxLevel = 4
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.SUBMERGED_MINING_SPEED, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.2 }
    }
}

class BlockBreakingSpeedMiningSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Block breaking speed"
    override val id = "block_breaking_speed"
    override val materialIcon = Material.REDSTONE
    override val description = listOf(mm("<gray>Increases your default block breaking speed"))
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.BLOCK_BREAK_SPEED, AttributeModifier.Operation.MULTIPLY_SCALAR_1) { level -> (level * 0.1) + 1 }
    }
}

class MiningExperienceMiningSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Mining Experience"
    override val id = "mining_experience"
    override val materialIcon = Material.EXPERIENCE_BOTTLE
    override val description = listOf(
        mm("<gray>Increases the amount of experience"),
        mm("<gray>gained by mining blocks")
    )
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.MINING_EXPERIENCE, this)
    }
}

val miningExperienceMap = mapOf(
    Material.COAL_ORE to Pair(2, 6),
    Material.DEEPSLATE_COAL_ORE to Pair(2, 6),
    Material.IRON_ORE to Pair(5, 8),
    Material.DEEPSLATE_IRON_ORE to Pair(5, 8),
    Material.COPPER_ORE to Pair(3, 7),
    Material.DEEPSLATE_COPPER_ORE to Pair(3, 7),
    Material.GOLD_ORE to Pair(8, 12),
    Material.DEEPSLATE_GOLD_ORE to Pair(8, 12),
    Material.REDSTONE_ORE to Pair(5, 8),
    Material.DEEPSLATE_REDSTONE_ORE to Pair(5, 8),
    Material.EMERALD_ORE to Pair(14, 25),
    Material.DEEPSLATE_EMERALD_ORE to Pair(14, 25),
    Material.LAPIS_ORE to Pair(5, 10),
    Material.DEEPSLATE_LAPIS_ORE to Pair(5, 10),
    Material.DIAMOND_ORE to Pair(12, 20),
    Material.DEEPSLATE_DIAMOND_ORE to Pair(12, 20),
    Material.NETHER_GOLD_ORE to Pair(2, 5),
    Material.NETHER_QUARTZ_ORE to Pair(1, 4),
    Material.ANCIENT_DEBRIS to Pair(25, 60),
    Material.AMETHYST_CLUSTER to Pair(5, 15),
)