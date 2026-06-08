package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.chanceOf
import com.helydra.evolutionPlugin.utils.checkCustomAttributes
import com.helydra.evolutionPlugin.utils.mm
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ExcavationSkill : Skill {
    override val name = "Excavation"
    override val id = "excavation"
    override val materialIcon = Material.DIAMOND_SHOVEL
    override val description = listOf(
        mm("<gray>Break blocks that are breakable"),
        mm("<gray>with a shovel"),
    )
    override val attributes = listOf(
        ExcavationExperienceExcavationSkillAttribute(this),
        TreasureHunterExcavationSkillAttribute(this),
    )
    override val spells = listOf<SkillSpell>()
}

class ExcavationExperienceExcavationSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Excavation Experience"
    override val id = "excavation_experience"
    override val materialIcon = Material.EXPERIENCE_BOTTLE
    override val description = listOf(
        mm("<gray>Increases the amount of experience"),
        mm("<gray>gained by mining blocks breakable"),
        mm("<gray>with a shovel")
    )
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.EXCAVATION_EXPERIENCE, this)
    }
}

class TreasureHunterExcavationSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Treasure Hunter"
    override val id = "treasure_hunter"
    override val materialIcon = Material.GOLD_INGOT
    override val description = listOf(
        mm("<gray>Enables you to find treasures by"),
        mm("<gray>mining blocks breakable with a shovel")
    )
    override val maxLevel = 5
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.TREASURE_HUNTER, this)
    }
}

fun getExcavationTreasure(): ItemStack {
    if (chanceOf(1, 1000)) return ItemStack(Material.ANCIENT_DEBRIS)
    if (chanceOf(1, 600)) return ItemStack(Material.NAUTILUS_SHELL)
    if (chanceOf(1, 300)) return ItemStack(Material.DIAMOND)
    if (chanceOf(1, 300)) return ItemStack(Material.EMERALD)
    if (chanceOf(1, 50)) return ItemStack(Material.GOLD_INGOT)
    if (chanceOf(1, 50)) return ItemStack(Material.IRON_INGOT)
    if (chanceOf(1, 50)) return ItemStack(Material.LAPIS_LAZULI)
    if (chanceOf(1, 25)) return ItemStack(Material.COAL)
    if (chanceOf(1, 20)) return ItemStack(Material.LEATHER)
    if (chanceOf(1, 10)) return ItemStack(Material.BONE)
    if (chanceOf(1, 10)) return ItemStack(Material.STRING)
    if (chanceOf(1, 10)) return ItemStack(Material.ROTTEN_FLESH)
    return ItemStack(Material.STICK)
}