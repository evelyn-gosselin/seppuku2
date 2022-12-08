package pw.seppuku.di.injectors

import pw.seppuku.di.AbstractDependencyInjector
import pw.seppuku.di.DependencyProvider
import kotlin.reflect.KClass

class SimpleDependencyInjector(
    dependencyTypeToDependencyProvider: Map<KClass<*>, DependencyProvider<out Any>>
) : AbstractDependencyInjector(dependencyTypeToDependencyProvider)