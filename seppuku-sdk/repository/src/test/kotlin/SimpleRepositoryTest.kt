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
    fun testFindByKeyOrNullReturnsBackingMapValue() {
        assertEquals("one", mockSimpleRepository.findByKeyOrNull(1))
        assertEquals("two", mockSimpleRepository.findByKeyOrNull(2))
        assertEquals("three", mockSimpleRepository.findByKeyOrNull(3))
        assertEquals("four", mockSimpleRepository.findByKeyOrNull(4))
        assertEquals("five", mockSimpleRepository.findByKeyOrNull(5))
    }
}