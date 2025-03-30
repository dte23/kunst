package com.example.kunsthandler.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.kunsthandler.models.Photo
import com.example.kunsthandler.models.SelectedPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class KunsthandlerUiState(
    val selectedPhotos: List<SelectedPhoto> = emptyList(),
    val currentPhoto: Photo? = null
)

class KunsthandlerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(KunsthandlerUiState())
    val uiState: StateFlow<KunsthandlerUiState> = _uiState.asStateFlow()

    init {
        println("KunsthandlerViewModel: Initialized with empty cart")
    }

    fun setCurrentPhoto(photo: Photo) {
        _uiState.update { it.copy(currentPhoto = photo) }
    }

    fun addToCart(selectedPhoto: SelectedPhoto) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedPhotos = currentState.selectedPhotos + selectedPhoto
            )
        }
    }

    fun removeFromCart(selectedPhoto: SelectedPhoto) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedPhotos = currentState.selectedPhotos - selectedPhoto
            )
        }
    }

    fun clearCart() {
        _uiState.update { it.copy(selectedPhotos = emptyList()) }
    }
} 