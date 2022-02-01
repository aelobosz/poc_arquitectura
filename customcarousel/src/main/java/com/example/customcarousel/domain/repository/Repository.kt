package com.example.customcarousel.domain.repository

import com.example.customcarousel.domain.model.Dog

interface Repository {
    suspend fun getCount(value: String): Dog

}