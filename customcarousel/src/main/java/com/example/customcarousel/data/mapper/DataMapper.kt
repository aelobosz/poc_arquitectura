package com.example.customcarousel.data.mapper

import com.example.customcarousel.data.datasource.remote.model.DogDTO
import com.example.customcarousel.domain.model.Dog

fun DogDTO.toDog(number: String) =
    Dog(number, message)