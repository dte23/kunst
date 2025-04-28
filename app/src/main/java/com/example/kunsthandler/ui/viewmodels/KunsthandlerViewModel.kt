package com.example.kunsthandler.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kunsthandler.models.Artist
import com.example.kunsthandler.models.Category
import com.example.kunsthandler.models.Photo
import com.example.kunsthandler.models.SelectedPhoto
import com.example.kunsthandler.network.KunsthandlerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Holds only the cart and current-photo state for now.
 * We’ll add loading/fetching later once the repository is wired up.
 */
data class KunsthandlerUiState(
    val selectedPhotos: List<SelectedPhoto> = emptyList(),
    val currentPhoto: Photo? = null
)

class KunsthandlerViewModel(
    private val repository: KunsthandlerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(KunsthandlerUiState())
    val uiState: StateFlow<KunsthandlerUiState> = _uiState.asStateFlow()

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    private val _photos = MutableLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> = _photos

    private val _frametypes = MutableLiveData<List<String>>()
    val frametypes: LiveData<List<String>> = _frametypes

    private val _photoSizes = MutableLiveData<List<String>>()
    val photoSizes: LiveData<List<String>> = _photoSizes

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchCategories()
        fetchArtists()
        fetchPhotos()
//        fetchFrameTypes()
//        fetchPhotoSizes()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            repository.getCategories()
                .onSuccess { _categories.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    private fun fetchArtists() {
        viewModelScope.launch {
            repository.getArtists()
                .onSuccess { _artists.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    private fun fetchPhotos() {
        viewModelScope.launch {
            repository.getPhotos()
                .onSuccess { _photos.value = it }
                .onFailure { _error.value = it.message }
        }
    }

//        private fun fetchFrameTypes() {
//        viewModelScope.launch {
//            repository.getFrameTypes()
//                .onSuccess { _frametypes.value = it }
//                .onFailure { _error.value = it.message }
//        }
//    }
//
//    private fun fetchPhotoSizes() {
//        viewModelScope.launch {
//            repository.getPhotoSizes()
//                .onSuccess { _photoSizes.value = it }
//                .onFailure { _error.value = it.message }
//        }
//    }

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

//fun getPhotosByArtist(artist: Artist): List<Photo> =
//    _photos.value?.filter { it.artist.id == artist.id } ?: emptyList()
//
///** Returns only the photos for this category */
//fun getPhotosByCategory(category: Category): List<Photo> =
//    _photos.value?.filter { it.category.id == category.id } ?: emptyList()
