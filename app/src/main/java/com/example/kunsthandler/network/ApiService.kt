package com.example.kunsthandler.network

interface ApiService {
    suspend fun getPhotos(): List<PhotoDto>
} 