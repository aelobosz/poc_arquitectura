package com.example.customcarousel.di.factory

import com.example.customcarousel.di.dependency.DependencyProvider

/**
 * alias for function that returns an instance type T
 */
typealias Build<T> = DependencyProvider.() -> T

class FactoryModule {
    private val _dependencies: MutableList<Pair<Class<*>, Build<Any>>> = mutableListOf()
    val dependencies: List<Pair<Class<*>, Build<Any>>> = _dependencies

    fun <T : Any> factory(clazz: Class<T>, build: Build<T>) {
        _dependencies.add(clazz to build)
    }

    inline fun <reified T : Any> factory(noinline build: Build<T>) {
        factory(T::class.java, build)
    }
}