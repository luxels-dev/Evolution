package com.helydra.evolutionPlugin.managers

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.interfaces.skills.*
import com.helydra.evolutionPlugin.utils.emptyItem
import com.helydra.evolutionPlugin.utils.mm
import com.helydra.evolutionPlugin.utils.playerHead
import org.bukkit.entity.Player

class SkillManager {

    val skillList = listOf(
        HuntingSkill(),
        MiningSkill(),
        WoodcuttingSkill(),
        ExcavationSkill(),
        FarmingSkill(),
        DefenceSkill(),
        ArcherySkill(),
        FishingSkill(),
        AdventuringSkill(),
        CareTakingSkill(),
        EnchantingSkill(),
        BrewingSkill(),
        TradingSkill()
    )

    fun spellFromId(id: String): SkillSpell? {
        for (skill in skillList) {
            for (spell in skill.spells) {
                if (spell.id == id) return spell
            }
        }
        return null
    }

    fun skillFromId(id: String): Skill? {
        for (skill in skillList) {
            if (skill.id == id) return skill
        }
        return null
    }

    fun spellsOfPlayer(player: Player): List<SkillSpell> {
        val list = mutableListOf<SkillSpell>()
        for (skill in skillList) {
            for (spell in skill.spells) {
                val level = spell.level(player) ?: 0
                if (level > 0) list.add(spell)
            }
        }
        return list
    }

    fun gui(player: Player): Gui {
        val title = mm("Skills Interface")
        val rows = 5
        val slots = mutableMapOf<Int, ItemSlot>()
        val emptySlots = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 17,
            18, 20, 26,
            27, 28, 29, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44
        )
        val skillSlots = listOf(
            12, 13, 14, 15, 16,
            21, 22, 23, 24, 25,
            30, 31, 32, 33, 34
        )
        val skillItems = skillList.map { skill -> ItemSlot(skill.icon(player)) {
            player.openGui(skillGui(player, skill))
        } }
        for (slot in emptySlots) {
            slots[slot] = ItemSlot(emptyItem) {}
        }
        slots[19] = ItemSlot(playerHead(player, mm("<gray>Skills of ${player.name}"))) {}
        val skillPageInstance = PageInstance(skillItems, skillSlots, 1, null, null)

        return Gui(title, rows, slots, listOf(skillPageInstance))
    }

    fun skillGui(player: Player, skill: Skill): Gui {
        val skillName = skill.name

        val title = mm("$skillName Skill Interface")
        val rows = 3
        val slots = mutableMapOf<Int, ItemSlot>()
        val emptySlots = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 11, 12, 16, 17,
            18, 19, 20, 21, 22, 23, 24, 25, 26
        )
        for (slot in emptySlots) {
            slots[slot] = ItemSlot(emptyItem) {}
        }
        slots[10] = ItemSlot(skill.icon(player)) {
            player.openGui(gui(player))
        }
        slots[13] = ItemSlot(skill.descriptionIcon()) {}
        slots[14] = ItemSlot(skill.attributesIcon()) { player.openGui(attributeGui(player, skill)) }
        slots[15] = ItemSlot(skill.spellsIcon()) { player.openGui(spellGui(player, skill)) }

        return Gui(title, rows, slots)
    }

    fun attributeGui(player: Player, skill: Skill): Gui {
        val skillName = skill.name

        val title = mm("$skillName Attributes")
        val rows = 5
        val slots = mutableMapOf<Int, ItemSlot>()
        val emptySlots = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 16, 17,
            18, 20, 21, 25, 26,
            27, 28, 29, 30, 34, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44
        )
        for (slot in emptySlots) {
            slots[slot] = ItemSlot(emptyItem) {}
        }
        val attributeSlots = listOf(
            13, 14, 15,
            22, 23, 24,
            31, 32, 33
        )
        val attributeItems = skill.attributes.map { attribute -> ItemSlot(attribute.icon(player)) { clickType ->
            if (clickType.isLeftClick && attribute.buyAttempt(player)) player.openGui(attributeGui(player, skill))
            else if (clickType.isRightClick && attribute.enableAttempt(player)) player.openGui(attributeGui(player, skill))
        } }
        slots[19] = ItemSlot(skill.icon(player)) {
            player.openGui(skillGui(player, skill))
        }
        val attributePageInstance = PageInstance(attributeItems, attributeSlots, 1, Pair(25, defaultRightPage), Pair(21, defaultLeftPage))
        return Gui(title, rows, slots, listOf(attributePageInstance))
    }

    fun spellGui(player: Player, skill: Skill): Gui {
        val skillName = skill.name

        val title = mm("$skillName Spells")
        val rows = 5
        val slots = mutableMapOf<Int, ItemSlot>()
        val emptySlots = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 10, 11, 12, 16, 17,
            18, 20, 21, 25, 26,
            27, 28, 29, 30, 34, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44
        )
        for (slot in emptySlots) {
            slots[slot] = ItemSlot(emptyItem) {}
        }
        val spellSlots = listOf(
            13, 14, 15,
            22, 23, 24,
            31, 32, 33
        )
        val spellItems = skill.spells.map { spell -> ItemSlot(spell.icon(player)) { clickType ->
            if (clickType.isLeftClick && spell.buyAttempt(player)) player.openGui(spellGui(player, skill))
        } }
        slots[19] = ItemSlot(skill.icon(player)) {
            player.openGui(skillGui(player, skill))
        }
        val spellPageInstance = PageInstance(spellItems, spellSlots, 1, Pair(25, defaultRightPage), Pair(21, defaultLeftPage))
        return Gui(title, rows, slots, listOf(spellPageInstance))
    }

}