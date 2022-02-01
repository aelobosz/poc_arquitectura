package com.example.customcarousel.data.datasource.remote

import com.example.customcarousel.data.datasource.remote.model.DogDTO

interface RemoteDataSource {
    suspend fun getServiceCount(value: String): DogDTO
}