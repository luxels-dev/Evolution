package com.helydra.evolutionPlugin.listeners.block

import com.helydra.evolutionPlugin.interfaces.skills.addFarmingExpFromBlock
import com.helydra.evolutionPlugin.interfaces.skills.getExcavationTreasure
import com.helydra.evolutionPlugin.interfaces.skills.miningExperienceMap
import com.helydra.evolutionPlugin.managers.SkillManager
import com.helydra.evolutionPlugin.utils.*
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

class BlockBreakListener : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val player = event.player
        val block = event.block

        if (!block.isMarked()) {
            val oreExpBoost = attributeLevel(player, CustomSkillAttribute.MINING_EXPERIENCE)
            if (oreList.contains(block.type)) {
                event.expToDrop += oreExpBoost
                val oreExpPair = miningExperienceMap[block.type]
                if (oreExpPair != null) SkillManager().skillFromId("mining")?.addExperience(player, (oreExpPair.first..oreExpPair.second).random())
            }
            val woodExpBoost = attributeLevel(player, CustomSkillAttribute.WOODCUTTING_EXPERIENCE)
            if (logList.contains(block.type)) {
                event.expToDrop += woodExpBoost
                val woodExpPair = Pair(5, 10)
                SkillManager().skillFromId("woodcutting")?.addExperience(player, (woodExpPair.first..woodExpPair.second).random())
            }
            if ((block.isPreferredTool(ItemStack(Material.DIAMOND_SHOVEL)))) {
                val excavationExpBoost = attributeLevel(player, CustomSkillAttribute.EXCAVATION_EXPERIENCE)
                event.expToDrop += excavationExpBoost
                val treasureHunterLevel = attributeLevel(player, CustomSkillAttribute.TREASURE_HUNTER)
                if (chanceOf(treasureHunterLevel, 8)) block.location.world.dropItem(block.location, getExcavationTreasure())
                val shovelExpPair = Pair(1, 3)
                SkillManager().skillFromId("excavation")?.addExperience(player, (shovelExpPair.first..shovelExpPair.second).random())
            }
            addFarmingExpFromBlock(player, block)
        }
        block.mark(false)
    }

}