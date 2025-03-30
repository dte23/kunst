package com.example.kunsthandler.data

fun calculateTotalPrice(selectedPhoto: SelectedPhoto): Float {
    val basePrice = selectedPhoto.photoPrice
    val framePrice = selectedPhoto.frameType.extraPrice
    val sizePrice = selectedPhoto.photoSize.extraPrice
    val widthContribution = selectedPhoto.frameWidth * 0.5f
    val total = basePrice + framePrice + sizePrice + widthContribution
    return total
} 