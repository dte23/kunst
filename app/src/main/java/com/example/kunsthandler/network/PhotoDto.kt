package com.example.kunsthandler.network

data class PhotoDto(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val artistId: String,
    val categoryId: String,
    val price: Double?,
    val createdAt: String,
    val updatedAt: String
) 