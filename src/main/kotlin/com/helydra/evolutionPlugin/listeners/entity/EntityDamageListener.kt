package com.helydra.evolutionPlugin.listeners.entity

import com.helydra.evolutionPlugin.managers.SkillManager
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.attributeLevel
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

class EntityDamageListener : Listener {

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent) {
        val entity = event.entity
        if (entity is Player) {
            SkillManager().skillFromId("defence")?.addExperience(entity, event.damage.toInt())
        }
        val player = event.damageSource.causingEntity as? Player ?: return
        val projectile = event.damageSource.directEntity
        if (projectile?.type != EntityType.ARROW || projectile.type != EntityType.SPECTRAL_ARROW) {
            event.damage += attributeLevel(player, CustomSkillAttribute.ARROW_DAMAGE)
            SkillManager().skillFromId("archery")?.addExperience(player, event.damage.toInt())
        }
    }

}