package pw.seppuku.client.components.client.gui.hud

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.util.math.MatrixStack

@JvmInline
value class InGameHudRender(val onInGameHudRender: TextRenderer.(matrices: MatrixStack) -> Unit)