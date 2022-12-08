package pw.seppuku.client.mixin

interface ActualThis<T> {

    @Suppress("UNCHECKED_CAST")
    val actualThis: T
        get() = this as T
}