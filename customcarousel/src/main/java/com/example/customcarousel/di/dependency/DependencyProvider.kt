package com.example.customcarousel.di.dependency

interface DependencyProvider {
    fun <T> get(clazz: Class<T>): T
    fun <T> getOrNull(clazz: Class<T>): T?
}

inline fun <reified T> DependencyProvider.get(): T {
    return get(T::class.java)
}

inline fun <reified T> DependencyProvider.getOrNull(): T? {
    return getOrNull(T::class.java)
}