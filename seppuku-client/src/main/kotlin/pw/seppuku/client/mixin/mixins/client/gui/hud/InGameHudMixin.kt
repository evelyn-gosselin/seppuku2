package pw.seppuku.client.mixin.mixins.client.gui.hud

import net.minecraft.client.gui.hud.InGameHud
import net.minecraft.client.util.math.MatrixStack
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.Inject
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo
import pw.seppuku.client.Seppuku
import pw.seppuku.client.components.client.gui.hud.InGameHudRender
import pw.seppuku.client.mixin.ActualThis
import pw.seppuku.components.Toggle
import pw.seppuku.feature.filterComponent
import pw.seppuku.feature.mapComponent

@Mixin(InGameHud::class)
abstract class InGameHudMixin : ActualThis<InGameHud> {

    @Inject(method = ["render"], at = [At("TAIL")])
    private fun onRenderTail(matrices: MatrixStack, tickDelta: Float, callback: CallbackInfo) =
        Seppuku.featureRepository.findAll()
            .filterComponent<Toggle>(false)
            .mapComponent<InGameHudRender>()
            .forEach { it.onInGameHudRender(actualThis, matrices, tickDelta) }
}