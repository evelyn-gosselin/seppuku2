package pw.seppuku.feature

import pw.seppuku.feature.internal.ReflectionComponentMapBuilder
import kotlin.reflect.KClass

abstract class AbstractFeature : Feature {

    private val components by lazy {
        ReflectionComponentMapBuilder.buildComponentMap(this)
    }

    @Suppress("UNCHECKED_CAST")
    final override fun <T : Any> findComponentOrNull(componentType: KClass<T>): T? = components[componentType] as? T
}