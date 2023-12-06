package com.example.spotifyclone.extensions

import android.graphics.Color
import kotlin.random.Random

fun Int.Companion.randomColor(): Int {
    return Color.argb(
        255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)
    )
}

fun Int.Companion.randomColor(string: String): Int {
    return Color.argb(
        255, string.hashCode().mod(100),string.uppercase().hashCode().mod(175), string.lowercase().hashCode().mod(100)
    )
}