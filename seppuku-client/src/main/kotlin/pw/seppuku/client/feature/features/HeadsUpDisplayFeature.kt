package pw.seppuku.client.feature.features

import pw.seppuku.client.components.client.gui.hud.InGameHudRender
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component

class HeadsUpDisplayFeature : AbstractFeature() {
    @Component
    private val humanIdentifier = HumanIdentifier("heads_up_display")

    @Component
    private val inGameHudRender = InGameHudRender { matrices ->
        drawWithShadow(matrices, "Seppuku", 2f, 2f, 0xffffff)
    }
}