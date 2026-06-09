package com.helydra.evolutionPlugin.listeners.player

import com.helydra.evolutionPlugin.managers.SkillManager
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.attributeLevel
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent

class PlayerFishListener : Listener {

    @EventHandler
    fun onPlayerFish(event: PlayerFishEvent) {
        val player = event.player
        val fishExpBoost = attributeLevel(player, CustomSkillAttribute.FISHING_EXPERIENCE)
        event.expToDrop *= fishExpBoost
        SkillManager().skillFromId("fishing")?.addExperience(player, (10..20).random())
    }

}