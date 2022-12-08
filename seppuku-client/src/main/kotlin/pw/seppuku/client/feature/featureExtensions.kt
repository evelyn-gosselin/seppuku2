package pw.seppuku.client.feature

import pw.seppuku.components.Toggle
import pw.seppuku.feature.Feature
import pw.seppuku.feature.findComponent
import kotlin.reflect.KClass

fun Iterable<Feature>.running(): List<Feature> =
    filter { it.findComponent<Toggle>()?.state ?: true }

fun <C : Any> Iterable<Feature>.withComponent(type: KClass<C>): List<C> =
    mapNotNull { it.findComponent(type) }

inline fun <reified C : Any> Iterable<Feature>.withComponent(): List<C> =
    withComponent(C::class)
