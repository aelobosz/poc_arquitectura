package com.example.customcarousel.data.datasource.remote

import com.example.customcarousel.data.datasource.remote.model.DogDTO
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getData(@Url url: String = "api/breeds/image/random"): DogDTO
}