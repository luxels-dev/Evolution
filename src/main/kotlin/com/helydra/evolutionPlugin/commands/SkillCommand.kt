package com.helydra.evolutionPlugin.commands

import com.helydra.evolutionPlugin.managers.SkillManager
import com.helydra.evolutionPlugin.managers.openGui
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class SkillCommand : TabExecutor {
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
        player.openGui(SkillManager().gui(player))
        return true
    }
}