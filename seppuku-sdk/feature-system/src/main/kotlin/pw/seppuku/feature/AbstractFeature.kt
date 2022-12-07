package pw.seppuku.feature

import pw.seppuku.feature.internal.ReflectionComponentMapBuilder
import kotlin.reflect.KClass

abstract class AbstractFeature : Feature {

    private val components by lazy {
        ReflectionComponentMapBuilder.buildComponentMap(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> findComponent(componentType: KClass<T>): T? = components[componentType] as? T
}