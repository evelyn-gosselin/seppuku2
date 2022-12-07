package pw.seppuku.di

import pw.seppuku.di.internal.DependencyResolver
import kotlin.reflect.KClass

abstract class AbstractDependencyInjector(
    private val dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
) : DependencyInjector {

    final override fun <T : Any> create(type: KClass<T>): T? =
        DependencyResolver.createBySuitableConstructor(type, dependencyTypeToDependencyProvider)
}