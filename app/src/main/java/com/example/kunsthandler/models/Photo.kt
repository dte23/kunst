package com.example.kunsthandler.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    @SerialName("id")
    val id: String,

    @SerialName("title")
    val title: String = "",

    @SerialName("imageThumbUrl")            // ← include the thumbnail if you ever need it
    val imageThumbUrl: String,

    @SerialName("imageUrl")
    val imageUrl: String,

    @SerialName("artistId")
    val artistId: String,                   // ← STRING, not Long

    @SerialName("categoryId")
    val categoryId: String,                 // ← STRING, not Int

    @SerialName("price")
    val price: Float = 0.0f
)
