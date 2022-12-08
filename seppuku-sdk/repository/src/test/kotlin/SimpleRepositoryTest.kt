import pw.seppuku.repository.repositories.SimpleRepository
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleRepositoryTest {

    private val backingMap: MutableMap<Int, String> = mutableMapOf(
        1 to "one",
        2 to "two",
        3 to "three",
        4 to "four",
        5 to "five",
    )

    private val mockSimpleRepository = SimpleRepository(backingMap)

    @Test
    fun testFindByKeyReturnsBackingMapValue() {
        assertEquals("one", mockSimpleRepository.findByKey(1))
        assertEquals("two", mockSimpleRepository.findByKey(2))
        assertEquals("three", mockSimpleRepository.findByKey(3))
        assertEquals("four", mockSimpleRepository.findByKey(4))
        assertEquals("five", mockSimpleRepository.findByKey(5))
    }
}