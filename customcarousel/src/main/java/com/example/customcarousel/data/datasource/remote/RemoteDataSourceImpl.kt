package com.example.customcarousel.data.datasource.remote

import com.example.customcarousel.data.datasource.remote.model.DogDTO
import kotlinx.coroutines.delay

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getServiceCount(value: String): DogDTO {
        val dog = apiService.getData()
        delay(1_000L)
        return dog
    }
}