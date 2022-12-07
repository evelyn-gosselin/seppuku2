package pw.seppuku.di

inline fun <reified T : Any> DependencyInjector.create(): T? = create(T::class)