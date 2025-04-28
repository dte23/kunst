//package com.example.kunsthandler.models
//
//import kotlinx.serialization.SerialName
//import kotlinx.serialization.Serializable
//
///**
// * Represents an artist fetched from the server.
// */
//@Serializable
//data class Artist(
//    @SerialName("id")        val id: Long,
//    @SerialName("firstName") val name: String,
//    @SerialName("lastName")  val familyName: String
//)
//
///**
// * Represents a photo category fetched from the server.
// */
//@Serializable
//data class Category(
//    @SerialName("id")   val id: Long,
//    @SerialName("name") val name: String
//)
//
///**
// * Represents a frame type fetched from the server.
// */
//@Serializable
//data class FrameType(
//    @SerialName("id")         val id: Long,
//    @SerialName("name")       val name: String,
//    @SerialName("color")      val colorHex: String,
//    @SerialName("extraPrice") val extraPrice: Float
//)
//
///**
// * Represents a photo size option fetched from the server.
// */
//@Serializable
//data class PhotoSize(
//    @SerialName("id")         val id: Long,
//    @SerialName("name")       val name: String,
//    @SerialName("size")       val size: Int,
//    @SerialName("extraPrice") val extraPrice: Float
//)
//
///**
// * Serializable data model for Photo, matching the JSON schema.
// * Contains only primitive IDs for artist and category; mapping to
// * full objects is done in the repository or ViewModel.
// */
//@Serializable
//data class Photo(
//    @SerialName("id")         val id: Long,
//    @SerialName("title")      val title: String,
//    @SerialName("imageUrl")   val imageUrl: String,
//    @SerialName("artistId")   val artistId: Long,
//    @SerialName("categoryId") val categoryId: Long,
//    @SerialName("price")      val price: Float
//)
//
///**
// * Represents a selected photo in the shopping cart,
// * including framing options and computed total price.
// */
//data class SelectedPhoto(
//    val photo: Photo,
//    val frameType: FrameType,
//    val frameWidth: Int,
//    val photoSize: PhotoSize,
//    val photoPrice: Float
//) {
//    /** Calculates the total price including base, frame, size, and width contrib. */
//    val totalPrice: Float
//        get() = photoPrice + frameType.extraPrice + photoSize.extraPrice + frameWidth * 0.5f
//}
