package pw.seppuku.components

data class Toggle(
    private val onStateChange: Boolean.() -> Unit,
    private val initialState: Boolean = false,
) {
    var state: Boolean = initialState
        set(value) {
            field = value
            onStateChange(value)
        }

    override fun equals(other: Any?) = when (other) {
        is Toggle -> other.state == state
        is Boolean -> other == state
        else -> super.equals(other)
    }

    override fun hashCode() = state.hashCode()

    override fun toString() = state.toString()
}