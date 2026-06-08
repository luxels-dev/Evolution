package com.helydra.evolutionPlugin.listeners.entity

import com.helydra.evolutionPlugin.interfaces.skills.huntingExperienceMap
import com.helydra.evolutionPlugin.managers.SkillManager
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.attributeLevel
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

class EntityDeathListener : Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity
        val attacker = event.damageSource.causingEntity ?: return
        if (attacker !is Player) return
        val expBoost = attributeLevel(attacker, CustomSkillAttribute.SLAYING_EXPERIENCE)
        event.droppedExp += expBoost
        val huntingExpRange = huntingExperienceMap[entity.type]
        if (huntingExpRange != null) SkillManager().skillFromId("hunting")?.addExperience(attacker, (huntingExpRange.first..huntingExpRange.second).random())
    }

}