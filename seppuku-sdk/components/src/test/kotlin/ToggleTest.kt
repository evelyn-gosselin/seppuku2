import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pw.seppuku.components.Toggle

class ToggleTest {

    private val backingBoolean = false
    private val toggle = Toggle(backingBoolean)

    @Test
    fun testEqualsMatchesBackingBoolean() {
        assert(toggle.equals(backingBoolean))
    }

    @Test
    fun testHashCodeMatchesBackingBooleanHashCode() {
        assertEquals(backingBoolean.hashCode(), backingBoolean.hashCode())
    }

    @Test
    fun testToStringMatchesBackingBooleanToString() {
        assertEquals(backingBoolean.toString(), toggle.toString())
    }
}