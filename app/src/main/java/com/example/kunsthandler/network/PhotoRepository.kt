// Rename to PhotosRepositoryImpl or NetworkPhotosRepository.kt
package com.example.kunsthandler.network

// Remove incorrect import
// import com.google.firebase.appdistribution.gradle.ApiService

class NetworkPhotosRepository(
    private val apiService: ApiService
) {
    /** Fetches a list of photos from the remote API. */
    suspend fun getPhotos(): Result<List<PhotoDto>> {
        return try {
            val photos = apiService.getPhotos()
            Result.success(photos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}