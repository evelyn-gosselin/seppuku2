package pw.seppuku.feature

inline fun <reified T : Any> Feature.findComponent(): T? = findComponent(T::class)