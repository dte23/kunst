package com.example.kunsthandler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kunsthandler.network.KunsthandlerApiService
import com.example.kunsthandler.network.KunsthandlerRepository
import com.example.kunsthandler.ui.KunsthandlerApp
import com.example.kunsthandler.ui.theme.AppTheme
import com.example.kunsthandler.ui.viewmodels.KunsthandlerViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {

    // Create the ApiService
    private val apiService: KunsthandlerApiService by lazy {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl("http://10.0.2.2:3000/") //
            .build()

        retrofit.create(KunsthandlerApiService::class.java)
    }

    // Create the repository (or get it from your DI framework)
    private val repository by lazy { KunsthandlerRepository(apiService) }

    // Create the ViewModelFactory
    private val viewModelFactory by lazy { KunsthandlerViewModelFactory(repository) }

    // Use ViewModelProvider with the custom factory
    private lateinit var viewModel: KunsthandlerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[KunsthandlerViewModel::class.java]

        setContent {
            AppTheme(dynamicColor = false) {
                // Pass your viewModel into the root composable
                KunsthandlerApp(viewModel = viewModel)
            }
        }
    }
}

// ViewModel Factory
class KunsthandlerViewModelFactory(
    private val repository: KunsthandlerRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(KunsthandlerViewModel::class.java)) {
            KunsthandlerViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}