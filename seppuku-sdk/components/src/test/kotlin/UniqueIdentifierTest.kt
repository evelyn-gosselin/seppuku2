import org.junit.jupiter.api.Test
import pw.seppuku.components.UniqueIdentifier
import java.util.UUID
import kotlin.test.assertEquals

class UniqueIdentifierTest {

    private val backingUuid = UUID.fromString("8db2dc21-6e3b-4f9e-8eed-0812f9d426a4")
    private val uniqueIdentifier = UniqueIdentifier(backingUuid)

    @Test
    fun testEqualsMatchesBackingUuid() {
        assert(uniqueIdentifier.equals(backingUuid))
    }

    @Test
    fun testHashCodeMatchesBackingUuidHashCode() {
        assertEquals(backingUuid.hashCode(), uniqueIdentifier.hashCode())
    }

    @Test
    fun testToStringMatchesBackingUuidToString() {
        assertEquals(backingUuid.toString(), uniqueIdentifier.toString())
    }
}