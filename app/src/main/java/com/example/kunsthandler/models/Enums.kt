package com.example.kunsthandler.models

import androidx.compose.ui.graphics.Color

enum class FrameType(val extraPrice: Float, val color: Color = Color.Yellow) {
    WOOD(0f, color = Color.Yellow),
    METAL(100f, color = Color.Blue),
    PLASTIC(30f, color = Color.Green)
}

enum class PhotoSize(val extraPrice: Float, val size: Int = 170) {
    SMALL(0f, size = 170),
    MEDIUM(130f, size = 200),
    LARGE(230f, size = 250)
}

enum class Category {
    NATURE,
    FOOD,
    SPORT
}