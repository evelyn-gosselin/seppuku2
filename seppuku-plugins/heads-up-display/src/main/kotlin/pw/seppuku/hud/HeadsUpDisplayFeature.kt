package pw.seppuku.hud

import pw.seppuku.client.components.client.gui.hud.InGameHudRender
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.filterComponent
import pw.seppuku.feature.mapComponent
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

            featureRepository.findAll()
                .filterComponent<Toggle> { it.state }
                .mapComponent<HumanIdentifier>()
                .map(HumanIdentifier::toString)
                .forEachIndexed { index, humanIdentifier ->
                    val x = 2f
                    val y = 2f + (index + 1) * 9
                    drawWithShadow(matrices, humanIdentifier, x, y, 0xffffff)
                }

        }
    }
}