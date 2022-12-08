package pw.seppuku.client

import net.minecraft.client.MinecraftClient
import pw.seppuku.client.feature.features.PluginLoaderFeature
import pw.seppuku.client.feature.repository.SeppukuFeatureRepository
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.di.DependencyInjector
import pw.seppuku.di.DependencyProvider
import pw.seppuku.di.create
import pw.seppuku.di.get
import pw.seppuku.di.injectors.SimpleDependencyInjector
import pw.seppuku.feature.findComponentOrNull
import pw.seppuku.feature.repository.FeatureRepository

object Seppuku {

    private val dependencyInjector = SimpleDependencyInjector().apply {
        bind(DependencyInjector::class to DependencyProvider.singleton(this))
        bind(FeatureRepository::class to DependencyProvider.singleton(SeppukuFeatureRepository()))

        bind(MinecraftClient::class to DependencyProvider { MinecraftClient.getInstance() })
    }

    val featureRepository: FeatureRepository by lazy { dependencyInjector.get() }

    init {
        val pluginLoaderFeature = dependencyInjector.create<PluginLoaderFeature>()
        pluginLoaderFeature.findComponentOrNull<HumanIdentifier>()?.run {
            featureRepository.save(
                toString(), pluginLoaderFeature
            )
        }
    }
}