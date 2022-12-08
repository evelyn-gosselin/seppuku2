package pw.seppuku.repository

interface Repository<K, V> {

    fun findAll(): Iterable<V>

    fun findByKey(key: K): V?

    fun save(key: K, value: V): V?
}