import org.junit.jupiter.api.Test
import pw.seppuku.di.DependencyProvider
import pw.seppuku.di.injectors.SimpleDependencyInjector
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@Suppress("unused")
class SimpleDependencyInjectorTest {

    class MetMockDependency

    class UnmetMockDependency

    class MockDependencyProvider : DependencyProvider<MetMockDependency> {
        override fun provide() = MetMockDependency()
    }

    class MetMockDependencyInjectionTarget(
        private val mockDependency: MetMockDependency
    )

    class OptionalMetMockDependencyInjectionTarget(
        private val mockDependency: MetMockDependency?
    )

    class UnmetMockDependencyInjectionTarget(
        private val mockDependency: UnmetMockDependency
    )

    class OptionalUnmetMockDependencyInjectionTarget(
        private val mockDependency: UnmetMockDependency?
    )

    private val mockDependencyInjector = SimpleDependencyInjector().apply {
        bind(MetMockDependency::class to MockDependencyProvider())
    }

    @Test
    fun testGetOrNullReturnsNotNullWhenDependenciesMet() {
        assertNotNull(mockDependencyInjector.getOrNull(MetMockDependency::class))
    }

    @Test
    fun testGetOrNullReturnsNullWhenDependenciesAreNotMet() {
        assertNull(mockDependencyInjector.getOrNull(UnmetMockDependency::class))
    }

    @Test
    fun testCreateOrNullReturnsNotNullWhenDependenciesAreMet() {
        assertNotNull(mockDependencyInjector.createOrNull(MetMockDependencyInjectionTarget::class))
        assertNotNull(mockDependencyInjector.createOrNull(OptionalMetMockDependencyInjectionTarget::class))
        assertNotNull(mockDependencyInjector.createOrNull(OptionalUnmetMockDependencyInjectionTarget::class))
    }

    @Test
    fun testCreateOrNullReturnsNullWhenDependenciesAreNotMet() {
        assertNull(mockDependencyInjector.createOrNull(UnmetMockDependencyInjectionTarget::class))
    }
}