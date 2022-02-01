package com.example.customcarousel.presentation.carousel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.customcarousel.domain.interactor.PrintUseCase
import com.example.customcarousel.domain.model.Dog
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive

class CarouselViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val printUseCase: PrintUseCase
) : ViewModel() {
    val tick = flow {
        var counter = 0
        while (currentCoroutineContext().isActive) {
            counter++
            val dog = printUseCase.doInBackground(counter.toString())
            Log.d(CarouselViewModel::class.java.name, counter.toString())
            emit(dog)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = 0
    )

}