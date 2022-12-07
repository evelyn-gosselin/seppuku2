package pw.seppuku.di

import kotlin.reflect.KClass

interface DependencyInjector {

    fun <T : Any> create(type: KClass<T>): T?
}