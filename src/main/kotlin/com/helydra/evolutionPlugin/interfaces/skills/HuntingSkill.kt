package com.helydra.evolutionPlugin.interfaces.skills

import com.helydra.evolutionPlugin.interfaces.Skill
import com.helydra.evolutionPlugin.interfaces.SkillAttribute
import com.helydra.evolutionPlugin.interfaces.SkillSpell
import com.helydra.evolutionPlugin.utils.CustomSkillAttribute
import com.helydra.evolutionPlugin.utils.checkCustomAttributes
import com.helydra.evolutionPlugin.utils.mm
import com.helydra.evolutionPlugin.utils.skillAttributeCheck
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class HuntingSkill : Skill {
    override val name = "Hunting"
    override val id = "hunting"
    override val materialIcon = Material.DIAMOND_SWORD
    override val description = listOf(mm("<gray>Kill mobs or animals"))
    override val attributes = listOf(
        StrengthHuntingSkillAttribute(this),
        AttackSpeedHuntingSkillAttribute(this),
        AttackKnockbackHuntingSkillAttribute(this),
        MobRangeHuntingSkillAttribute(this),
        SlayingExperienceHuntingSkillAttribute(this)
    )
    override val spells = listOf<SkillSpell>(
        TestHuntingSpellAttribute(this)
    )
}

class StrengthHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Strength"
    override val id = "strength"
    override val materialIcon = Material.REDSTONE
    override val description = listOf(mm("<gray>Increases the damage you deal"))
    override val maxLevel = 10
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.ATTACK_DAMAGE, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 1.0 }
    }
}

class AttackSpeedHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Attack Speed"
    override val id = "attack_speed"
    override val materialIcon = Material.SUGAR
    override val description = listOf(mm("<gray>Increases your attack speed"))
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.ATTACK_SPEED, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 1.0 }
    }
}

class AttackKnockbackHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Attack Knockback"
    override val id = "attack_knockback"
    override val materialIcon = Material.GUNPOWDER
    override val description = listOf(mm("<gray>Increases your attack knockback"))
    override val maxLevel = 8
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.ATTACK_KNOCKBACK, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 0.5 }
    }
}

class MobRangeHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Mob range"
    override val id = "mob_range"
    override val materialIcon = Material.LAPIS_LAZULI
    override val description = listOf(mm("<gray>Increases your entity range"))
    override val maxLevel = 5
    override val pointsPerLevel = 2
    override fun check(player: Player) {
        skillAttributeCheck(player, this, Attribute.ENTITY_INTERACTION_RANGE, AttributeModifier.Operation.ADD_NUMBER) { level -> level * 1.0 }
    }
}

class SlayingExperienceHuntingSkillAttribute(override val skill: Skill) : SkillAttribute {
    override val name = "Slaying Experience"
    override val id = "slaying_experience"
    override val materialIcon = Material.EXPERIENCE_BOTTLE
    override val description = listOf(
        mm("<gray>Increases the amount of experience"),
        mm("<gray>gained by slaying mobs")
    )
    override val maxLevel = 10
    override val pointsPerLevel = 1
    override fun check(player: Player) {
        checkCustomAttributes(player, CustomSkillAttribute.SLAYING_EXPERIENCE, this)
    }
}

class TestHuntingSpellAttribute(override val skill: Skill) : SkillSpell {
    override val name = "Test"
    override val id = "test"
    override val materialIcon = Material.BOOK
    override val description = listOf(
        mm("<gray>This is a test")
    )
    override val maxLevel = 1
    override val pointsPerLevel = 1
    override val manaCostPerLevel = 10
    override fun cast(player: Player) {
        player.sendMessage("Yow")
    }
}

val huntingExperienceMap = mapOf(
    EntityType.CHICKEN to Pair(4, 5),
    EntityType.COW to Pair(4, 7),
    EntityType.PIG to Pair(4, 6),
    EntityType.SHEEP to Pair(4, 6),
    EntityType.CAMEL to Pair(4, 10),
    EntityType.DONKEY to Pair(4, 8),
    EntityType.HORSE to Pair(4, 8),
    EntityType.MULE to Pair(4, 8),
    EntityType.CAT to Pair(4, 5),
    EntityType.PARROT to Pair(4, 5),
    EntityType.WOLF to Pair(4, 5),
    EntityType.ARMADILLO to Pair(4, 5),
    EntityType.BAT to Pair(4, 5),
    EntityType.BEE to Pair(4, 5),
    EntityType.FOX to Pair(4, 5),
    EntityType.GOAT to Pair(4, 10),
    EntityType.LLAMA to Pair(4, 8),
    EntityType.OCELOT to Pair(4, 5),
    EntityType.PANDA to Pair(4, 7),
    EntityType.POLAR_BEAR to Pair(4, 10),
    EntityType.RABBIT to Pair(4, 8),
    EntityType.AXOLOTL to Pair(4, 5),
    EntityType.COD to Pair(1, 2),
    EntityType.DOLPHIN to Pair(4, 5),
    EntityType.FROG to Pair(4, 5),
    EntityType.GLOW_SQUID to Pair(4, 5),
    EntityType.NAUTILUS to Pair(4, 5),
    EntityType.PUFFERFISH to Pair(1, 2),
    EntityType.SALMON to Pair(1, 2),
    EntityType.SQUID to Pair(1, 2),
    EntityType.TADPOLE to Pair(1, 1),
    EntityType.TROPICAL_FISH to Pair(1, 2),
    EntityType.TURTLE to Pair(4, 5),
    EntityType.ALLAY to Pair(4, 5),
    EntityType.MOOSHROOM to Pair(4, 8),
    EntityType.SNIFFER to Pair(4, 20),
    EntityType.IRON_GOLEM to Pair(4, 10),
    EntityType.TRADER_LLAMA to Pair(4, 8),
    EntityType.BOGGED to Pair(1, 5),
    EntityType.CAMEL_HUSK to Pair(1, 5),
    EntityType.DROWNED to Pair(1, 5),
    EntityType.HUSK to Pair(1, 5),
    EntityType.PARCHED to Pair(1, 5),
    EntityType.SKELETON to Pair(1, 5),
    EntityType.SKELETON_HORSE to Pair(1, 5),
    EntityType.STRAY to Pair(1, 5),
    EntityType.ZOMBIE to Pair(1, 5),
    EntityType.ZOMBIE_HORSE to Pair(1, 5),
    EntityType.ZOMBIE_NAUTILUS to Pair(1, 5),
    EntityType.ZOMBIE_VILLAGER to Pair(1, 5),
    EntityType.CAVE_SPIDER to Pair(1, 5),
    EntityType.SPIDER to Pair(1, 5),
    EntityType.BREEZE to Pair(4, 5),
    EntityType.CREAKING to Pair(4, 5),
    EntityType.CREEPER to Pair(1, 5),
    EntityType.ELDER_GUARDIAN to Pair(30, 50),
    EntityType.GUARDIAN to Pair(4, 5),
    EntityType.PHANTOM to Pair(4, 5),
    EntityType.SILVERFISH to Pair(1, 1),
    EntityType.SLIME to Pair(1, 1),
    EntityType.WARDEN to Pair(500, 2000),
    EntityType.WITCH to Pair(4, 5),
    EntityType.EVOKER to Pair(4, 20),
    EntityType.PILLAGER to Pair(4, 5),
    EntityType.RAVAGER to Pair(4, 20),
    EntityType.VEX to Pair(4, 20),
    EntityType.VINDICATOR to Pair(4, 20),
    EntityType.BLAZE to Pair(4, 5),
    EntityType.GHAST to Pair(4, 5),
    EntityType.HOGLIN to Pair(4, 5),
    EntityType.MAGMA_CUBE to Pair(1, 1),
    EntityType.PIGLIN to Pair(4, 5),
    EntityType.PIGLIN_BRUTE to Pair(4, 20),
    EntityType.STRIDER to Pair(4, 5),
    EntityType.WITHER_SKELETON to Pair(4, 10),
    EntityType.ZOGLIN to Pair(4, 5),
    EntityType.ZOMBIFIED_PIGLIN to Pair(4, 5),
    EntityType.ENDERMAN to Pair(4, 5),
    EntityType.ENDERMITE to Pair(1, 1),
    EntityType.SHULKER to Pair(4, 5),
    EntityType.WITHER to Pair(200, 500),
    EntityType.ENDER_DRAGON to Pair(400, 1000),
)