package pw.seppuku.di

import kotlin.reflect.KClass

class DependencyInjectorBuilder<T : DependencyInjector>(
    private val dependencyInjectorType: KClass<T>,
) {

    private val dependencyTypeToDependencyProvider: MutableMap<KClass<*>, DependencyProvider<out Any>> = mutableMapOf()

    init {
        if (dependencyInjectorType.isAbstract)
            error("Can't use abstract dependency injector '${dependencyInjectorType.simpleName}' with '${this::class.simpleName}'")
    }

    fun with(
        dependencyTypeToDependencyProviderPair: Pair<KClass<*>, DependencyProvider<out Any>>
    ): DependencyInjectorBuilder<T> =
        with(dependencyTypeToDependencyProviderPair.first, dependencyTypeToDependencyProviderPair.second)

    fun with(
        dependencyType: KClass<*>,
        dependencyProvider: DependencyProvider<out Any>
    ): DependencyInjectorBuilder<T> {
        dependencyTypeToDependencyProvider[dependencyType] = dependencyProvider
        return this
    }

    fun build(): T = dependencyInjectorType.constructors
        .first { it.parameters.size == 1 }
        .call(dependencyTypeToDependencyProvider)
}