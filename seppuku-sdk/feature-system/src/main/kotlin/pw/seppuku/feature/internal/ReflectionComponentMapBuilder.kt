package pw.seppuku.feature.internal

import pw.seppuku.feature.Component
import pw.seppuku.feature.Feature
import kotlin.reflect.KClass
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.jvmErasure
import kotlin.reflect.jvm.kotlinProperty

internal object ReflectionComponentMapBuilder {

    internal fun <F : Feature> buildComponentMap(feature: F): Map<KClass<*>, Any?> {
        val featureClass = feature::class

        // For some reason using KProperty#hasAnnotation does not work.
        val componentProperties =
            featureClass.java.declaredFields.filter { it.isAnnotationPresent(Component::class.java) }.mapNotNull {
                it.kotlinProperty
            }

        return componentProperties.map {
            it.isAccessible = true
            it
        }.associate {
            Pair(it.returnType.jvmErasure, it.call(feature))
        }
    }
}