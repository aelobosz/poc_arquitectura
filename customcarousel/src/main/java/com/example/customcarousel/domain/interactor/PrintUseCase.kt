package com.example.customcarousel.domain.interactor

import com.example.customcarousel.domain.model.Dog
import com.example.customcarousel.domain.interactor.base.BaseUseCase
import com.example.customcarousel.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class PrintUseCase(
    private val repository: Repository,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseUseCase<Dog, String>(ioDispatcher) {
    override suspend fun doInBackground(params: String): Dog {
        val response = repository.getCount(params)
        //logica
        return  response
    }

    override fun failureStateHandler(throwable: Throwable): Dog = Dog()
}