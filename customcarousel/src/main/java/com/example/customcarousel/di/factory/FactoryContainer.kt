package com.example.customcarousel.di.factory

import java.lang.Exception

class FactoryContainer(modules: List<FactoryModule>) {
    private val factories: Map<Class<*>, Build<Any>>

    init {
        val factoryList: MutableList<Pair<Class<*>, Build<Any>>> = mutableListOf()
        modules.forEach {
            factoryList += it.dependencies
        }
        factories = factoryList.toMap()
    }

    operator fun <T> get(clazz: Class<T>): Build<T> {
        return factories[clazz] as? Build<T>
            ?: throw FactoryModuleNotFoundException(clazz)

    }
}

class FactoryModuleNotFoundException(clazz: Class<*>) :
    Exception("factory for class ${clazz.name} not found")