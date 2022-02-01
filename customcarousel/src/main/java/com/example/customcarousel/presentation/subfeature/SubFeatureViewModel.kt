package com.example.customcarousel.presentation.subfeature

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.customcarousel.domain.interactor.AnotherUseCase

class SubFeatureViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val anotherUseCase: AnotherUseCase
) : ViewModel() {
}