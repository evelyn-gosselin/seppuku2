package pw.seppuku.client.mixin.mixins.client.gui.hud

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.util.math.MatrixStack
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import pw.seppuku.client.Seppuku
import pw.seppuku.client.components.client.gui.hud.InGameHudRender
import pw.seppuku.client.mixin.ActualThis
import pw.seppuku.feature.findComponent

@Mixin(InGameHud::class)
abstract class InGameHudMixin : ActualThis<InGameHud> {

    @Shadow
    abstract fun getTextRenderer(): TextRenderer

    @Inject(method = ["render"], at = [At("TAIL")])
    private fun onRenderTail(matrices: MatrixStack, tickDelta: Float, callback: CallbackInfo) =
        Seppuku.featureRepository.findAll()
            .mapNotNull { it.findComponent<InGameHudRender>() }
            .forEach { it.onInGameHudRender(getTextRenderer(), matrices) }
}