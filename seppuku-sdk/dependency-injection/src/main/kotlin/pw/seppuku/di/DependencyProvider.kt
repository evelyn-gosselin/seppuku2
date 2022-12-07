package pw.seppuku.di

interface DependencyProvider<T : Any> {

    fun provide(): T?

    companion object {
        fun <T : Any> singleton(instance: T): DependencyProvider<T> {
            return object : DependencyProvider<T> {
                override fun provide(): T = instance
            }
        }
    }
}