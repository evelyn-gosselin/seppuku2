package pw.seppuku.client

import pw.seppuku.client.feature.features.HeadsUpDisplayFeature
import pw.seppuku.client.feature.repository.SeppukuFeatureRepository
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.di.DependencyProvider
import pw.seppuku.di.create
import pw.seppuku.di.dependencyInjectorBuilder
import pw.seppuku.di.get
import pw.seppuku.di.injectors.SimpleDependencyInjector
import pw.seppuku.feature.findComponent
import pw.seppuku.feature.repository.FeatureRepository

object Seppuku {

    private val dependencyInjector = dependencyInjectorBuilder<SimpleDependencyInjector>()
        .with(FeatureRepository::class to DependencyProvider.singleton(SeppukuFeatureRepository()))
        .build()

    val featureRepository: FeatureRepository by lazy { dependencyInjector.get() }

    init {
        val headsUpDisplayFeature = dependencyInjector.create<HeadsUpDisplayFeature>()
        headsUpDisplayFeature.findComponent<HumanIdentifier>()?.run {
            featureRepository.save(
                toString(),
                headsUpDisplayFeature
            )
        }
    }
}