package pw.seppuku.client.components.client.gui.hud

import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.util.math.MatrixStack

@JvmInline
value class InGameHudRender(val onInGameHudRender: InGameHud.(matrices: MatrixStack, tickDelta: Float) -> Unit)