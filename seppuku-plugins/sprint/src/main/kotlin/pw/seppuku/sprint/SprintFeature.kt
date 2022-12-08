package pw.seppuku.sprint

import net.minecraft.client.network.ClientPlayerEntity
import pw.seppuku.client.components.client.network.ClientPlayerEntityTick
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.PluginFeature

@PluginFeature
class SprintFeature : AbstractFeature() {

    @Component
    private val humanIdentifier = HumanIdentifier("sprint")

    @Component
    private val toggle = Toggle()

    @Component
    private val clientPlayerEntityTick = ClientPlayerEntityTick {
        isSprinting = shouldSprint()
    }

    private fun ClientPlayerEntity.shouldSprint(): Boolean =
        input.hasForwardMovement() && !input.sneaking
}