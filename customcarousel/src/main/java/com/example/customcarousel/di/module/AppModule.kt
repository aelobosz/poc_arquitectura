package com.example.customcarousel.di.module

import com.example.customcarousel.data.datasource.local.LocalDataSource
import com.example.customcarousel.data.datasource.local.LocalDataSourceImpl
import com.example.customcarousel.data.datasource.remote.ApiService
import com.example.customcarousel.data.datasource.remote.RemoteDataSource
import com.example.customcarousel.data.datasource.remote.RemoteDataSourceImpl
import com.example.customcarousel.data.repository.RepositoryImpl
import com.example.customcarousel.di.dependency.get
import com.example.customcarousel.di.locator.ServiceLocator
import com.example.customcarousel.domain.interactor.PrintUseCase
import com.example.customcarousel.domain.repository.Repository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = ServiceLocator.module {
    factory { PrintUseCase(get()) }
    factory<Repository> {
        RepositoryImpl(
            get(),
            get()
        )
    }
    factory<LocalDataSource> { LocalDataSourceImpl() }
    factory<RemoteDataSource> {
        RemoteDataSourceImpl(
            get()
        )
    }
    factory<ApiService> {
        Retrofit.Builder()
            .baseUrl("https://dog.ceo")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
