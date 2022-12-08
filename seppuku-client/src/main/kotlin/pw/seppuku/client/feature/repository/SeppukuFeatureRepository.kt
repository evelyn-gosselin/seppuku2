package pw.seppuku.client.feature.repository

import net.minecraft.client.MinecraftClient
import pw.seppuku.feature.Feature
import pw.seppuku.feature.repository.FeatureRepository

class SeppukuFeatureRepository(
    backingMap: MutableMap<String, Feature> = mutableMapOf()
) : FeatureRepository(
    backingMap.toSortedMap(FEATURE_KEY_LENGTH_COMPARATOR)
) {

    companion object {
        private val FEATURE_KEY_LENGTH_COMPARATOR = Comparator.comparingInt<String> {
            MinecraftClient.getInstance().textRenderer.getWidth(it)
        }
    }
}