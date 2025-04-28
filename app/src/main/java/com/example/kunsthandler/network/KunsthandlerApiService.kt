package com.example.kunsthandler.network
import com.example.kunsthandler.models.Artist
import com.example.kunsthandler.models.Category
import com.example.kunsthandler.models.FrameType
import com.example.kunsthandler.models.Photo
import com.example.kunsthandler.models.PhotoSize
import retrofit2.http.GET

interface KunsthandlerApiService {
    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("artists")
    suspend fun getArtists(): List<Artist>

    @GET("photos")
    suspend fun getPhotos(): List<Photo>

    @GET("FrameTypes")
    suspend fun getFrameTypes(): List<FrameType>

    @GET("PhotoSizes")
    suspend fun getPhotoSizes(): List<PhotoSize>

}

