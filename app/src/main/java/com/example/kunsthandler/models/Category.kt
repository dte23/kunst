package com.example.kunsthandler.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String
)