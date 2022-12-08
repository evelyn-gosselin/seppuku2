package pw.seppuku.client.components.client

import net.minecraft.client.Keyboard

@JvmInline
value class KeyboardOnKey(
    val onKeyboardOnKey: Keyboard.(Long, Int, Int, Int, Int) -> Unit
)
