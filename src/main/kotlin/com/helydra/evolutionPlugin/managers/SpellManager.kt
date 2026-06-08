package com.helydra.evolutionPlugin.managers

import com.helydra.evolutionPlugin.data
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.emptyItem
import com.helydra.evolutionPlugin.utils.hideUselessInfo
import com.helydra.evolutionPlugin.utils.mm
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.inventory.ItemStack

class SpellManager {

    fun gui(player: Player): Gui {
        val title = mm("Spell Interface")
        val rows = 5
        val slots = mutableMapOf<Int, ItemSlot>()
        val emptySlots = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 11, 13, 15, 17,
            18, 19, 20, 21, 22, 23, 24, 25, 26,
            27, 29, 31, 33, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44
        )
        val spellSlots = listOf(
            10, 12, 14, 16,
            28, 30, 32, 34
        )
        val spellItems = spellCombos.map { combo -> ItemSlot(spellIcon(player, combo)) { clickType ->
            if (clickType.isLeftClick) player.openGui(spellPickGui(player, combo))
            else if (clickType.isRightClick) {
                removeSpellSlot(player, combo)
                player.openGui(gui(player))
            }
        } }
        for (slot in emptySlots) {
            slots[slot] = ItemSlot(emptyItem) {}
        }
        val skillPageInstance = PageInstance(spellItems, spellSlots, 1, null, null)

        return Gui(title, rows, slots, listOf(skillPageInstance))
    }

    val spellCombos = listOf(
        SpellCombo(firstIsLeft = true, secondIsLeft = true, thirdIsLeft = true),
        SpellCombo(firstIsLeft = true, secondIsLeft = true, thirdIsLeft = false),
        SpellCombo(firstIsLeft = true, secondIsLeft = false, thirdIsLeft = true),
        SpellCombo(firstIsLeft = true, secondIsLeft = false, thirdIsLeft = false),
        SpellCombo(firstIsLeft = false, secondIsLeft = true, thirdIsLeft = true),
        SpellCombo(firstIsLeft = false, secondIsLeft = true, thirdIsLeft = false),
        SpellCombo(firstIsLeft = false, secondIsLeft = false, thirdIsLeft = true),
        SpellCombo(firstIsLeft = false, secondIsLeft = false, thirdIsLeft = false)
    )

    fun spellIcon(player: Player, combo: SpellCombo): ItemStack = ItemStack(Material.PAPER).apply {
        val spellId = spellSlot(player, combo)
        val spell = spellId?.let { SkillManager().spellFromId(it) }
        val meta = itemMeta
        meta.itemName(mm("<color:#9572C2>Spell : <color:#DACEED>${spell?.name ?: "Click to pick"}"))
        val lore = mutableListOf<Component>()
        if (spell != null) lore.addAll(spell.description)
        lore.addAll(combo.getList().map { bool -> if (bool) mm("<color:#EEDEFA>← <color:#95ACE8>Left <color:#A79FAB>click") else mm("<color:#EEDEFA>→ <color:#EB9BA2>Right <color:#A79FAB>click") })
        meta.lore(lore)
        itemMeta = meta
        if (spell != null) return this.withType(Material.NETHER_STAR)
    }

    fun spellSlot(player: Player, combo: SpellCombo) = data.get<String>("spell_slots.${player.uniqueId}.${combo.firstIsLeft};${combo.secondIsLeft};${combo.thirdIsLeft}")
    fun setSpellSlot(player: Player, combo: SpellCombo, spellId: String) = data.set("spell_slots.${player.uniqueId}.${combo.firstIsLeft};${combo.secondIsLeft};${combo.thirdIsLeft}", spellId)
    fun removeSpellSlot(player: Player, combo: SpellCombo) = data.remove("spell_slots.${player.uniqueId}.${combo.firstIsLeft};${combo.secondIsLeft};${combo.thirdIsLeft}")

    fun spellPickGui(player: Player, combo: SpellCombo): Gui {
        val title = mm("Spell Picking Interface")
        val rows = 5
        val slots = mutableMapOf<Int, ItemSlot>()
        val emptySlots = listOf(
            0, 1, 2, 3, 4, 5, 6, 7, 8,
            9, 17,
            18, 26,
            27, 35,
            36, 37, 38, 39, 40, 41, 42, 43, 44
        )
        val spellSlots = listOf(
            10, 11, 12, 13, 14, 15, 16,
            19, 20, 21, 22, 23, 24, 25,
            28, 29, 30, 31, 32, 33, 34
        )
        val spellItems = SkillManager().spellsOfPlayer(player).map { spell -> ItemSlot(spellPickingIcon(player, spell)) {
            setSpellSlot(player, combo, spell.id)
            player.openGui(gui(player))
        } }
        for (slot in emptySlots) {
            slots[slot] = ItemSlot(emptyItem) {}
        }
        val skillPageInstance = PageInstance(spellItems, spellSlots, 1, Pair(44, defaultRightPage), Pair(36, defaultLeftPage))

        return Gui(title, rows, slots, listOf(skillPageInstance))
    }

    fun spellPickingIcon(player: Player, spell: SkillSpell) = ItemStack(spell.materialIcon).apply {
        val meta = itemMeta
        val level = spell.level(player) ?: 0
        meta.itemName(mm("<color:#55919D>Spell : <color:#B0E7F3>${spell.name}"))
        val lore = mutableListOf(
            mm("<color:#55919D> ● Level : <color:#B0E7F3>$level")
        )
        if (level > 0) lore.addLast(mm("<color:#55919D> ● Mana cost : <color:#B0E7F3>${spell.manaCostPerLevel * level}<color:#55919D>"))
        lore.addAll(spell.description)
        meta.lore(lore)
        meta.hideUselessInfo()
        itemMeta = meta
    }

    val comboMap = mutableMapOf<Player, MutableList<Boolean>>()

    fun handleWandClick(player: Player, action: Action) {
        if (!comboMap.containsKey(player)) comboMap[player] = mutableListOf()
        var bool: Boolean? = null
        if (action.isLeftClick) bool = true
        if (action.isRightClick) bool = false
        val combo = comboMap[player] ?: return
        combo.addLast(bool ?: return)
        if (combo.size >= 3) {
            val spellId = spellSlot(player, SpellCombo(combo[0], combo[1], combo[2]))
            val spell = spellId?.let { SkillManager().spellFromId(it) }
            if (spell != null) {
                player.showTitle(Title.title(mm("<color:#DACEED>${spell.name}"), mm(""), 0, 20, 8))
                spell.cast(player)
            } else player.showTitle(Title.title(mm("<red>No spell (/spell)"), mm(""), 0, 40, 8))
            combo.clear()
        } else {
            val first = combo.getOrNull(0)
            val second = combo.getOrNull(1)
            val third = combo.getOrNull(2)
            var firstString = "<color:#EEDEFA>?"
            var secondString = "<color:#EEDEFA>?"
            var thirdString = "<color:#EEDEFA>?"
            if (first == true) firstString = "<color:#95ACE8>←" else if (first == false) firstString = "<color:#EB9BA2>→"
            if (second == true) secondString = "<color:#95ACE8>←" else if (second == false) secondString = "<color:#EB9BA2>→"
            if (third == true) thirdString = "<color:#95ACE8>←" else if (third == false) thirdString = "<color:#EB9BA2>→"
            player.showTitle(Title.title(mm("$firstString $secondString $thirdString"), mm(""), 0, 20 * 4, 8))
        }
    }

}

class SpellCombo(val firstIsLeft: Boolean, val secondIsLeft: Boolean, val thirdIsLeft: Boolean) {
    fun getList() = listOf(firstIsLeft, secondIsLeft, thirdIsLeft)
}

class SpellPickGui(name: Component, rows: Int, slots: Map<Int, ItemSlot>, pageInstances: List<PageInstance>, val combo: SpellCombo) : Gui(name, rows, slots, pageInstances)