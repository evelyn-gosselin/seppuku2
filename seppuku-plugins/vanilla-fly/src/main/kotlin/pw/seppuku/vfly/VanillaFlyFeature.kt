package pw.seppuku.vfly

import pw.seppuku.client.components.client.network.ClientPlayerEntityTick
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.components.Toggle
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.PluginFeature

@PluginFeature
class VanillaFlyFeature : AbstractFeature() {

    @Component
    private val humanIdentifier = HumanIdentifier("vanilla_fly")

    @Component
    private val toggle = Toggle()

    @Component
    private val clientPlayerEntityTick = ClientPlayerEntityTick {
        this.abilities.allowFlying = true
    }
}