package pw.seppuku.di

inline fun <reified T : DependencyInjector> dependencyInjectorBuilder() = DependencyInjectorBuilder(T::class)

inline fun <reified T : Any> DependencyInjectorBuilder<*>.with(dependencyProvider: DependencyProvider<T>) =
    this.with(T::class, dependencyProvider)