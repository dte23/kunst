package com.example.kunsthandler

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kunsthandler.models.Category
import com.example.kunsthandler.data.ArtDataSource
import com.example.kunsthandler.models.FrameType
import com.example.kunsthandler.models.PhotoSize
import com.example.kunsthandler.models.SelectedPhoto
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Test
    fun navigateToPhotoDetails_changeFrameType_priceUpdates() {
        // Start from home screen and navigate to category selection
        composeTestRule.onNodeWithText("Kategori").performClick()

        // Select NATURE category
        composeTestRule.onNodeWithText("NATURE").performClick()

        // Click on first photo
        composeTestRule.onAllNodes(hasClickAction())[0].performClick()

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("Velg ramme og størrelse").assertIsDisplayed()
        composeTestRule.onNodeWithTag("frame_type_wood").assertExists()

        // Calculate initial price
        val selectedPhoto = SelectedPhoto(
            photo = ArtDataSource.photosByCategory(Category.NATURE)[0],
            frameType = FrameType.WOOD,
            frameWidth = 10,
            photoSize = PhotoSize.SMALL,
            photoPrice = ArtDataSource.photosByCategory(Category.NATURE)[0].price
        )
        val initialPrice = selectedPhoto.totalPrice

        // Change frame type and size
        composeTestRule.onNodeWithTag("frame_type_metal").performClick()
        composeTestRule.onNodeWithTag("photo_size_large").performClick()
        composeTestRule.onNodeWithTag("frame_width_20").performClick()

        // Calculate expected new price
        val updatedPhoto = selectedPhoto.copy(
            frameType = FrameType.METAL,
            frameWidth = 20,
            photoSize = PhotoSize.LARGE
        )
        val expectedNewPrice = updatedPhoto.totalPrice

        // Verify price has changed
        Assert.assertNotEquals(
            "Price should change after changing frame type",
            initialPrice,
            expectedNewPrice
        )
    }

    @Test
    fun navigateToArtistImages_selectPhoto_verifyDetails() {
        // Start from home screen and navigate to artist selection
        composeTestRule.onNodeWithTag("nav_artist").performClick()

        // Select first artist
        composeTestRule.onAllNodes(hasClickAction())[0].performClick()

        // Click on first photo in the grid
        composeTestRule.onAllNodes(hasClickAction())[0].performClick()

        // Verify we're on the details screen
        composeTestRule.onNodeWithText("Velg ramme og størrelse").assertIsDisplayed()
        composeTestRule.onNodeWithTag("frame_type_wood").assertExists()
        composeTestRule.onNodeWithTag("photo_size_small").assertExists()
        composeTestRule.onNodeWithText("Velg rammebredde").assertIsDisplayed()
    }

    @Test
    fun navigateToCart_completePayment_verifySuccess() {
        // Start from home screen and navigate to category selection
        composeTestRule.onNodeWithText("Kategori").performClick()

        // Select NATURE category
        composeTestRule.onNodeWithText("NATURE").performClick()

        // Select a photo
        composeTestRule.onAllNodes(hasClickAction())[0].performClick()

        // Customize the photo
        composeTestRule.onNodeWithTag("frame_type_metal").performClick()
        composeTestRule.onNodeWithTag("photo_size_large").performClick()
        composeTestRule.onNodeWithTag("frame_width_20").performClick()

        // Add to cart
        composeTestRule.onNodeWithTag("add_to_cart").performClick()
        
        // Wait for UI to update
        Thread.sleep(1000) // Wait a full second
        composeTestRule.waitForIdle()

        // Verify cart is not empty
        composeTestRule.onNodeWithText("Handlekurven er tom").assertDoesNotExist()
        composeTestRule.onNodeWithTag("cart_count").assertExists()
        composeTestRule.onNodeWithTag("cart_count").assertTextContains("Antall bilder: 1")

        // Go to payment
        composeTestRule.onNodeWithTag("nav_payment").performClick()

        // Verify we're on the payment screen with total price and pay button
        composeTestRule.onNodeWithText("Totalpris:").assertExists()
        composeTestRule.onNodeWithText("Betal!").assertExists()

        // Complete payment
        composeTestRule.onNodeWithText("Betal!").performClick()

        // Verify we're back at start screen with empty cart
        composeTestRule.onNodeWithText("Handlekurven er tom").assertExists()
    }
}

 