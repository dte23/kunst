package com.example.kunsthandler.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    @SerialName("id")
    val id: String,

    @SerialName("firstName")
    val name: String = "",

    @SerialName("lastName")
    val familyName: String = ""
)