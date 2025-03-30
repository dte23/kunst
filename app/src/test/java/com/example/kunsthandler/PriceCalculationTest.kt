package com.example.kunsthandler

import com.example.kunsthandler.models.*
import org.junit.Assert.assertEquals
import org.junit.Test

class PriceCalculationTest {
    
    @Test
    fun calculatePrice_smallPhotoWithWoodFrame() {
        val basePrice = 1200.0f
        val photo = Photo(
            id = 1L,
            title = "Test Photo",
            imageResId = 0,
            artist = Artist(1, "Test", "Artist"),
            category = Category.NATURE,
            price = basePrice
        )
        
        val selectedPhoto = SelectedPhoto(
            photo = photo,
            frameType = FrameType.WOOD,
            frameWidth = 10,
            photoSize = PhotoSize.SMALL,
            photoPrice = basePrice
        )
        
        // Expected: basePrice + WOOD(0) + SMALL(0) + (10mm * 0.5)
        val expectedPrice = 1200.0f + 0f + 0f + (10 * 0.5f)
        assertEquals(expectedPrice, selectedPhoto.totalPrice, 0.01f)
    }
    
    @Test
    fun calculatePrice_mediumPhotoWithMetalFrame() {
        val basePrice = 800.0f
        val photo = Photo(
            id = 2L,
            title = "Test Photo",
            imageResId = 0,
            artist = Artist(1, "Test", "Artist"),
            category = Category.FOOD,
            price = basePrice
        )
        
        val selectedPhoto = SelectedPhoto(
            photo = photo,
            frameType = FrameType.METAL,
            frameWidth = 15,
            photoSize = PhotoSize.MEDIUM,
            photoPrice = basePrice
        )
        
        // Expected: basePrice + METAL(100) + MEDIUM(130) + (15mm * 0.5)
        val expectedPrice = 800.0f + 100f + 130f + (15 * 0.5f)
        assertEquals(expectedPrice, selectedPhoto.totalPrice, 0.01f)
    }
    
    @Test
    fun calculatePrice_largePhotoWithPlasticFrame() {
        val basePrice = 1500.0f
        val photo = Photo(
            id = 3L,
            title = "Test Photo",
            imageResId = 0,
            artist = Artist(1, "Test", "Artist"),
            category = Category.SPORT,
            price = basePrice
        )
        
        val selectedPhoto = SelectedPhoto(
            photo = photo,
            frameType = FrameType.PLASTIC,
            frameWidth = 20,
            photoSize = PhotoSize.LARGE,
            photoPrice = basePrice
        )
        
        // Expected: basePrice + PLASTIC(30) + LARGE(230) + (20mm * 0.5)
        val expectedPrice = 1500.0f + 30f + 230f + (20 * 0.5f)
        assertEquals(expectedPrice, selectedPhoto.totalPrice, 0.01f)
    }
} 