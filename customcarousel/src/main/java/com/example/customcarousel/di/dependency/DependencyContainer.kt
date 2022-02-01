package com.example.customcarousel.di.dependency

class DependencyContainer : DependencyBinder {
    private val dependencies: MutableMap<Class<*>, Any?> = mutableMapOf()

    operator fun <T> get(clazz:Class<T>): T? {
        return dependencies[clazz] as? T
    }

    override fun instance(obj: Any) {
        dependencies[obj::class.java] = obj
    }

    override fun <T> instanceNullable(clazz: Class<T>, obj: T?) {
        dependencies[clazz] = obj
    }

    override fun <T> bind(clazz: Class<T>, obj: T) {
        dependencies[clazz] = obj
    }
}