package pw.seppuku.client.feature.features

import pw.seppuku.client.components.client.MinecraftClientInit
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.repository.FeatureRepository

class ExternalFeatureLoaderFeature(
    private val featureRepository: FeatureRepository
) : AbstractFeature() {
    @Component
    private val humanIdentifier = HumanIdentifier("external_feature_loader")

    @Component
    private val minecraftClientInit = MinecraftClientInit {
        // TODO: Load external features
    }
}