import org.junit.jupiter.api.Test
import pw.seppuku.components.HumanIdentifier
import kotlin.test.assertEquals

class HumanIdentifierTest {

    private val backingString = "abc"
    private val humanIdentifier = HumanIdentifier(backingString)

    @Test
    fun testEqualsMatchesBackingString() {
        assert(humanIdentifier.equals(backingString))
    }

    @Test
    fun testHashCodeMatchesBackingStringHashCode() {
        assertEquals(backingString.hashCode(), humanIdentifier.hashCode())
    }

    @Test
    fun testToStringMatchesBackingStringToString() {
        assertEquals(backingString, humanIdentifier.toString())
    }
}