package pw.seppuku.feature

import kotlin.reflect.KClass

interface Feature {

    fun <T : Any> findComponent(componentType: KClass<T>): T?
}