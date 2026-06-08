package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.managers.SkillManager
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.attributeLevel
import com.helydra.evolutionPlugin.utils.checkCustomAttributes
import com.helydra.evolutionPlugin.utils.mm
import com.helydra.evolutionPlugin.utils.playerHead
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.Ageable
import org.bukkit.entity.Player

class FarmingSkill : Skill {
    override val name = "Farming"
    override val id = "farming"
    override val materialIcon = Material.DIAMOND_HOE
    override val description = listOf(mm("<gray>Harvest crops"))
    override val attributes = listOf(
        FarmingExperienceFarmingSkillAttribute(this)
    )
    override val spells = listOf<SkillSpell>()
}

class FarmingExperienceFarmingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Farming experience"
    override val id = "farming_experience"
    override val materialIcon = Material.EXPERIENCE_BOTTLE
    override val description = listOf(mm("<gray>Gives you exp by harvesting crops"))
    override val maxLevel = 3
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.FARMING_EXPERIENCE, this)
    }
}

fun addFarmingExpFromBlock(player: Player, block: Block) {
    val ageable = block.blockData as? Ageable
    if (ageable != null && ageable.age != ageable.maximumAge) return

    if (listOf(
            Material.SWEET_BERRY_BUSH,
            Material.COCOA,
            Material.KELP,
            Material.KELP_PLANT,
            Material.VINE,
            Material.TWISTING_VINES,
            Material.TWISTING_VINES_PLANT,
            Material.WEEPING_VINES,
            Material.WEEPING_VINES_PLANT,
            Material.MELON,
            Material.PUMPKIN,
            Material.SUGAR_CANE,
            Material.CACTUS,
            Material.BAMBOO,
            Material.BAMBOO_SAPLING,
            Material.CHORUS_PLANT,
            Material.CHORUS_FLOWER,
            Material.NETHER_WART,
            Material.WHEAT,
            Material.CARROTS,
            Material.POTATOES,
            Material.BEETROOTS
    ).contains(block.type)) {
        SkillManager().skillFromId("farming")?.addExperience(player, (1..3).random())
        val farmingExpLevel = attributeLevel(player, CustomSkillAttribute.FARMING_EXPERIENCE)
        player.giveExp(farmingExpLevel, true)
    }
}