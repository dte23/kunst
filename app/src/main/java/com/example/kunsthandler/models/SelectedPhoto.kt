package com.example.kunsthandler.models

import kotlinx.serialization.Serializable

@Serializable
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