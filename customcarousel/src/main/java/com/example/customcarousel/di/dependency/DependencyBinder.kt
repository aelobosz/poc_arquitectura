package com.example.customcarousel.di.dependency

interface DependencyBinder {
    fun instance(obj: Any)
    fun <T> instanceNullable(clazz: Class<T>, obj: T?)
    fun <T> bind(clazz: Class<T>, obj: T)
}