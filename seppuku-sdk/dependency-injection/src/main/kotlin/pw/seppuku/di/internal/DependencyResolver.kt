package pw.seppuku.di.internal

import pw.seppuku.di.DependencyProvider
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

internal object DependencyResolver {

    internal fun <T : Any> createBySuitableConstructor(
        type: KClass<T>,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): T? {
        val suitableConstructor = findSuitableConstructor(type, dependencyTypeToDependencyProvider) ?: return null
        val dependencies = resolveDependencies(suitableConstructor, dependencyTypeToDependencyProvider)
        return suitableConstructor.call(*dependencies)
    }

    private fun <T : Any> findSuitableConstructor(
        type: KClass<T>,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): KFunction<T>? = type.constructors
        .associateBy { resolveDependencies(it, dependencyTypeToDependencyProvider) }
        .toSortedMap(Comparator.comparingInt { it.size })
        .firstNotNullOfOrNull { it.value }

    private fun <T : Any> resolveDependencies(
        constructor: KFunction<T>,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): Array<Any?> = constructor.parameters
        .map { it.type.classifier }
        .map { dependencyTypeToDependencyProvider[it] }
        .map { it?.provide() }
        .toTypedArray()
}