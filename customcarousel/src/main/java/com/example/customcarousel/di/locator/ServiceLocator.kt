package com.example.customcarousel.di.locator

import com.example.customcarousel.di.dependency.DependencyBinder
import com.example.customcarousel.di.dependency.DependencyContainer
import com.example.customcarousel.di.dependency.DependencyProvider
import com.example.customcarousel.di.dependency.get
import com.example.customcarousel.di.factory.FactoryContainer
import com.example.customcarousel.di.factory.FactoryModule

inline fun <reified T> lazyLocate(serviceLocator: ServiceLocator): Lazy<T> =
    lazy { serviceLocator.get() }

class ServiceLocator(vararg modules: FactoryModule) : DependencyProvider {
    private val dependencyContainer: DependencyContainer = DependencyContainer()
    private val factoryContainer: FactoryContainer = FactoryContainer(modules.toList())
    override fun <T> get(clazz: Class<T>): T =
        dependencyContainer[clazz] ?: buildDependency(clazz)


    override fun <T> getOrNull(clazz: Class<T>): T? = dependencyContainer[clazz]

    private fun <T> buildDependency(clazz: Class<T>): T {
        val factory = factoryContainer[clazz]
        val dependency = this.factory()
        dependencyContainer.bind(clazz, dependency)
        return dependency
    }

    fun bindDependency(binder: (DependencyBinder) -> Unit) {
        binder(dependencyContainer)
    }

    companion object {
        fun module(build: FactoryModule.() -> Unit): FactoryModule =
            FactoryModule().apply(build)
    }
}