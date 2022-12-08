package pw.seppuku.feature

import kotlin.reflect.KClass

interface Feature {

    fun <T : Any> findComponentOrNull(componentType: KClass<T>): T?

    fun <T : Any> findComponent(componentType: KClass<T>): T =
        findComponentOrNull(componentType)
            ?: error("Could not get component '${componentType.simpleName}' of feature '${this::class.simpleName}'")
}