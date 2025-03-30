package com.example.kunsthandler.models

import androidx.annotation.DrawableRes

data class Artist(
    val id: Long,
    val name: String = "",
    val familyName: String = ""
)

data class Photo(
    val id: Long,
    val title: String = "",
    @DrawableRes
    val imageResId: Int,
    val artist: Artist,
    val category: Category,
    val price: Float = 0.0f
)

data class SelectedPhoto(
    val photo: Photo,
    val frameType: FrameType,
    val frameWidth: Int,
    val photoSize: PhotoSize,
    val photoPrice: Float
) {
    val totalPrice: Float
        get() = calculateTotalPrice(this)
} 