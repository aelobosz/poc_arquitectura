package com.example.customcarousel.presentation.carousel

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import com.bumptech.glide.Glide
import com.example.customcarousel.R
import com.example.customcarousel.di.factory.ViewModelFactory
import com.example.customcarousel.di.locator.ServiceLocator
import com.example.customcarousel.di.locator.lazyLocate
import com.example.customcarousel.di.module.appModule
import com.example.customcarousel.domain.model.Dog
import com.example.customcarousel.domain.interactor.PrintUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CarouselView : FrameLayout {
    constructor(context: Context) : super(context) {
        initialize(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        initialize(context)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet?,
        defStyleAttr: Int
    ) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        initialize(context)
    }

    private var scope: CoroutineScope? = null
    private var serviceLocator = ServiceLocator(appModule)
    private val printUseCase: PrintUseCase by lazyLocate(serviceLocator)
    private val viewModel by lazy {
        val factory = ViewModelFactory(
            printUseCase = printUseCase,
            owner = findViewTreeSavedStateRegistryOwner()!!
        )
        ViewModelProvider(findViewTreeViewModelStoreOwner()!!, factory)
            .get(CarouselViewModel::class.java)
    }
    private val textView: TextView by lazy { findViewById(R.id.delta) }
    private val imageView: ImageView by lazy { findViewById(R.id.imageView) }

    private fun initialize(context: Context) {
        inflate(context, R.layout.my_custom_view, this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        viewModel.tick
            .onEach { tick ->
                val dog = tick as? Dog
                textView.text = dog?.number
                Glide
                    .with(this)
                    .load(dog?.image)
                    .centerCrop()
                    .into(imageView);

            }
            .launchIn(scope!!)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        scope?.cancel()
        scope = null
    }


}

