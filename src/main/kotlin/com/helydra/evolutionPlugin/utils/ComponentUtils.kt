package com.helydra.evolutionPlugin.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage

fun mm(text: String): Component =
    MiniMessage.miniMessage().deserialize(text).decoration(TextDecoration.ITALIC, false)