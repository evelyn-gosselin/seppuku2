package pw.seppuku.di

import pw.seppuku.di.internal.DependencyResolver
import kotlin.reflect.KClass
import kotlin.reflect.full.starProjectedType

abstract class AbstractDependencyInjector(
    private val dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
) : DependencyInjector {

    @Suppress("UNCHECKED_CAST")
    final override fun <T : Any> getOrNull(type: KClass<T>): T? =
        DependencyResolver.resolveDependency(
            type.starProjectedType.classifier,
            dependencyTypeToDependencyProvider
        ) as? T

    final override fun <T : Any> createOrNull(type: KClass<T>): T? =
        DependencyResolver.createBySuitableConstructor(type, dependencyTypeToDependencyProvider)
}