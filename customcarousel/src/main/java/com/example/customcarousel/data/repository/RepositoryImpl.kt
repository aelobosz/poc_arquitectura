package com.example.customcarousel.data.repository

import com.example.customcarousel.data.datasource.local.LocalDataSource
import com.example.customcarousel.data.datasource.remote.RemoteDataSource
import com.example.customcarousel.data.mapper.toDog
import com.example.customcarousel.domain.model.Dog
import com.example.customcarousel.domain.repository.Repository

class RepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override suspend fun getCount(value: String): Dog =
        remoteDataSource.getServiceCount(value).toDog(value)
}