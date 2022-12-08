package pw.seppuku.feature

import kotlin.reflect.KClass

inline fun <reified T : Any> Feature.findComponentOrNull(): T? = findComponentOrNull(T::class)

inline fun <reified T : Any> Feature.findComponent(): T = findComponent(T::class)

fun <C : Any> Iterable<Feature>.filterComponent(
    type: KClass<C>,
    strict: Boolean = true,
    predicate: (C) -> Boolean
): List<Feature> =
    filter {
        val component = it.findComponentOrNull(type) ?: return@filter !strict
        predicate(component)
    }

inline fun <reified C : Any> Iterable<Feature>.filterComponent(
    strict: Boolean = true,
    noinline predicate: (C) -> Boolean
): List<Feature> =
    filterComponent(C::class, strict, predicate)

fun <C : Any> Iterable<Feature>.filterComponent(type: KClass<C>, strict: Boolean = true): List<Feature> =
    filterComponent(type, strict) { true }

inline fun <reified C : Any> Iterable<Feature>.filterComponent(strict: Boolean = true): List<Feature> =
    filterComponent(C::class, strict)

fun <C : Any> Iterable<Feature>.mapComponent(type: KClass<C>): List<C> =
    mapNotNull { it.findComponentOrNull(type) }

inline fun <reified C : Any> Iterable<Feature>.mapComponent(): List<C> =
    mapComponent(C::class)
