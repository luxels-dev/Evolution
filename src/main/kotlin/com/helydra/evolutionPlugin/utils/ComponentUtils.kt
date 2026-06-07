package com.helydra.evolutionPlugin.utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage

fun mm(text: String): Component =
    MiniMessage.miniMessage().deserialize(text).decoration(TextDecoration.ITALIC, false)

fun loadingBar(filledColor: String, emptyColor: String, value: Int, maxValue: Int, amount: Int): String {
    val progress = ((value.toDouble() / maxValue.toDouble()) * amount)
        .toInt()
        .coerceIn(0, amount)

    val filled = "|".repeat(progress)
    val empty = "|".repeat(amount - progress)

    return "$filledColor$filled$emptyColor$empty"
}
