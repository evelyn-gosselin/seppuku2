package pw.seppuku.repository.repositories

import pw.seppuku.repository.AbstractRepository

class SimpleRepository<K, V>(
    private val backingMap: MutableMap<K, V> = mutableMapOf()
) : AbstractRepository<K, V>() {
    override fun findAll(): Iterable<V> = backingMap.values

    override fun findByKey(key: K): V? = backingMap[key]

    override fun save(key: K, value: V): V? = backingMap.put(key, value)
}