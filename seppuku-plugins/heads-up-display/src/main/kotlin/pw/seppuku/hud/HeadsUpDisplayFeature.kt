package pw.seppuku.hud

import pw.seppuku.client.components.client.gui.hud.InGameHudRender
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.repository.FeatureRepository

class HeadsUpDisplayFeature(
    private val featureRepository: FeatureRepository,
) : AbstractFeature() {
    @Component
    private val humanIdentifier = HumanIdentifier("heads_up_display")

    @Component
    private val inGameHudRender = InGameHudRender { matrices, _ ->
        textRenderer.run {
            drawWithShadow(matrices, "seppuku", 2f, 2f, 0xffffff)
        }
    }
}