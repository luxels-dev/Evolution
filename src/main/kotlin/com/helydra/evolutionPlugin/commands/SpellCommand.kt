package com.helydra.evolutionPlugin.commands

import com.helydra.evolutionPlugin.managers.openGui
import com.helydra.evolutionPlugin.spellManager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class SpellCommand : TabExecutor {

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String?> {
        return emptyList()
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val player = sender as? Player ?: return true
        player.openGui(spellManager.gui(player))
        return true
    }

}