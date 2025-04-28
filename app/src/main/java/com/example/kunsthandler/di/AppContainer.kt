

package com.example.kunsthandler.di

import com.example.kunsthandler.network.KunsthandlerApiService
import com.example.kunsthandler.network.KunsthandlerRepository
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Manual DI container:
 * - Provides network dependencies
 * - Builds repositories and viewmodel factories
 */

class AppContainer(

) {
    // Network
    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private val apiService: KunsthandlerApiService by lazy {
        retrofit.create(KunsthandlerApiService::class.java)
    }

    // Repository
    private val kunsthandlerRepository: KunsthandlerRepository by lazy {
        KunsthandlerRepository(apiService)
    }

    // ViewModelFactory
    val kunsthandlerViewModelFactory: KunsthandlerViewModel by lazy {
        KunsthandlerViewModel(kunsthandlerRepository)
    }
}



