package com.example.customcarousel.domain.interactor.base

import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<out State, in Params>(
    private val coroutineContext: CoroutineContext
) {
    abstract suspend fun doInBackground(params: Params): State
    abstract fun failureStateHandler(throwable: Throwable): State

    suspend fun execute(params: Params = None as Params): State =
        withContext(coroutineContext) {
            return@withContext try {
                doInBackground(params)
            } catch (e: Throwable) {
                failureStateHandler(e)
            }
        }
}

typealias None = Unit?
