package pw.seppuku.repository

interface Repository<K, V> {

    fun findByKey(key: K): V =
        findByKeyOrNull(key) ?: error("Could not get value by key '$key'")

    fun findAll(): Iterable<V>

    fun findByKeyOrNull(key: K): V?

    fun save(key: K, value: V): V?
}