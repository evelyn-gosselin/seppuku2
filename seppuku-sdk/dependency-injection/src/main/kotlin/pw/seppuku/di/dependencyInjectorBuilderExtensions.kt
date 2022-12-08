package pw.seppuku.di

inline fun <reified T : DependencyInjector> dependencyInjectorBuilder() =
    DependencyInjectorBuilder(T::class)