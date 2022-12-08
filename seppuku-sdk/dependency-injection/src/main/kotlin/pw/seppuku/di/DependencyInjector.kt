package pw.seppuku.di

import kotlin.reflect.KClass

interface DependencyInjector {

    fun bind(dependencyTypeToDependencyProviderPair: Pair<KClass<*>, DependencyProvider<out Any>>) =
        bind(dependencyTypeToDependencyProviderPair.first, dependencyTypeToDependencyProviderPair.second)

    fun bind(dependencyType: KClass<*>, dependencyProvider: DependencyProvider<out Any>)

    fun <T : Any> getOrNull(type: KClass<T>): T?

    fun <T : Any> createOrNull(type: KClass<T>): T?

    fun <T : Any> get(type: KClass<T>): T =
        getOrNull(type) ?: error("Could not get dependency '${type.simpleName}'")

    fun <T : Any> create(type: KClass<T>): T =
        createOrNull(type) ?: error("Could not create dependency consumer '${type.simpleName}'")
}