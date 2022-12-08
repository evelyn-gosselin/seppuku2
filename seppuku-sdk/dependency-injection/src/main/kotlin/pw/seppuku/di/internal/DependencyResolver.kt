package pw.seppuku.di.internal

import pw.seppuku.di.DependencyProvider
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KFunction

internal object DependencyResolver {

    internal fun <T : Any> createBySuitableConstructor(
        type: KClass<T>,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): T? {
        val suitableConstructor = findSuitableConstructor(type, dependencyTypeToDependencyProvider) ?: return null
        val dependencies = resolveDependencies(suitableConstructor, dependencyTypeToDependencyProvider) ?: return null
        return suitableConstructor.call(*dependencies)
    }

    private fun <T : Any> findSuitableConstructor(
        type: KClass<T>,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): KFunction<T>? = type.constructors
        .associateBy { resolveDependencies(it, dependencyTypeToDependencyProvider) }
        .filterKeys { it != null }
        .toSortedMap(Comparator.comparingInt { it!!.size })
        .firstNotNullOfOrNull { it.value }

    private fun <T : Any> resolveDependencies(
        constructor: KFunction<T>,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): Array<Any?>? = constructor.parameters
        .map {
            val dependency = resolveDependency(it.type.classifier, dependencyTypeToDependencyProvider)
            if (dependency == null && !it.type.isMarkedNullable)
                return@resolveDependencies null

            dependency
        }
        .toTypedArray()

    internal fun resolveDependency(
        type: KClassifier?,
        dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
    ): Any? = dependencyTypeToDependencyProvider[type]?.provide()
}