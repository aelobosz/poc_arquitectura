package com.example.customcarousel.di.factory

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.customcarousel.domain.interactor.AnotherUseCase
import com.example.customcarousel.domain.interactor.PrintUseCase
import com.example.customcarousel.presentation.carousel.CarouselViewModel
import com.example.customcarousel.presentation.subfeature.SubFeatureViewModel


class ViewModelFactory @JvmOverloads constructor(
    private val printUseCase: PrintUseCase? = null,
    private val anotherUseCase: AnotherUseCase? = null,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return when (modelClass) {
            CarouselViewModel::class.java -> CarouselViewModel(handle, printUseCase!!)
            SubFeatureViewModel::class.java -> SubFeatureViewModel(handle, anotherUseCase!!)
            else -> {
                throw ClassNotFoundException("ViewModel ${modelClass.name} Not Found on ViewModelFactory")
            }
        } as T
    }
}