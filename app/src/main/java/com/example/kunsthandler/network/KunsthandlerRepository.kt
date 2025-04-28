package com.example.kunsthandler.network

import com.example.kunsthandler.models.Artist
import com.example.kunsthandler.models.Category
import com.example.kunsthandler.models.FrameType
import com.example.kunsthandler.models.Photo
import com.example.kunsthandler.models.PhotoSize

class KunsthandlerRepository(
    private val apiService: KunsthandlerApiService
) {

    suspend fun getCategories(): Result<List<Category>> {
        return try {
            val categories = apiService.getCategories()
            Result.success(categories)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getArtists(): Result<List<Artist>> {
        return try {
            val artists = apiService.getArtists()
            Result.success(artists)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPhotos(): Result<List<Photo>> {
        return try {
            val photos = apiService.getPhotos()
            Result.success(photos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFrameTypes(): Result<List<FrameType>> {
        return try {
            val frameTypes = apiService.getFrameTypes()
            Result.success(frameTypes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getPhotoSizes(): Result<List<PhotoSize>> {
        return try {
            val photoSizes = apiService.getPhotoSizes()
            Result.success(photoSizes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}






