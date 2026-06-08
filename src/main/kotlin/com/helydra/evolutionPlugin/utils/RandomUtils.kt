package com.helydra.evolutionPlugin.utils

import kotlin.random.Random

fun chanceOf(x: Int, y: Int) = Random.nextInt(y) < x
