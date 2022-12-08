import org.junit.jupiter.api.Test
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@Suppress("unused")
class AbstractFeatureTest {

    class MetMockComponent

    class UnmetMockComponent

    class MockFeature : AbstractFeature() {

        @Component
        private val mockComponent = MetMockComponent()
    }

    private val mockFeature = MockFeature()

    @Test
    fun testFindComponentOrNullReturnsNotNullWhenComponentMet() {
        assertNotNull(mockFeature.findComponentOrNull(MetMockComponent::class))
    }

    @Test
    fun testFindComponentOrNullReturnsNullWhenComponentNotMet() {
        assertNull(mockFeature.findComponentOrNull(UnmetMockComponent::class))
    }
}