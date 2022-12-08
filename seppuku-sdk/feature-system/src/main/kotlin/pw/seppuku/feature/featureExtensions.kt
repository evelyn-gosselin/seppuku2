package pw.seppuku.feature

inline fun <reified T : Any> Feature.findComponentOrNull(): T? = findComponentOrNull(T::class)

inline fun <reified T : Any> Feature.findComponent(): T = findComponent(T::class)
