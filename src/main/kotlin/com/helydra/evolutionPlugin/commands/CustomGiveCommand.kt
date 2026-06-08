package com.helydra.evolutionPlugin.commands

import com.helydra.evolutionPlugin.managers.magicWand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class CustomGiveCommand : TabExecutor {

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String?> {
        return if (args.size == 1) items.keys.toList()
        else emptyList()
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        val player = sender as? Player ?: return true
        if (args.size != 1) return true
        val name = args[0]
        val item = items[name] ?: return true
        player.give(item)
        return true
    }

    val items = mapOf(
        "magic_wand" to magicWand
    )

}