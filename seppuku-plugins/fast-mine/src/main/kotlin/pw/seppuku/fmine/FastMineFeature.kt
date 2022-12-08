package pw.seppuku.fmine

import pw.seppuku.client.access.mixin.mixins.client.network.IClientPlayerInteractionManagerMixin
import pw.seppuku.client.components.client.network.ClientPlayerInteractionManagerUpdateBlockBreakingProgress
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.PluginFeature

@PluginFeature
class FastMineFeature : AbstractFeature() {

    @Component
    private val humanIdentifier = HumanIdentifier("fast_mine")

    @Component
    private val toggle = Toggle()

    @Component
    private val clientPlayerInteractionManagerUpdateBlockBreakingProgress =
        ClientPlayerInteractionManagerUpdateBlockBreakingProgress { _, _ ->
            this as IClientPlayerInteractionManagerMixin
            setCurrentBreakingProgress(getCurrentBreakingProgress() + 0.1f)
        }
}