package pw.seppuku.feature.repository

import pw.seppuku.feature.Feature
import pw.seppuku.repository.repositories.SimpleRepository

abstract class FeatureRepository(
    backingMap: MutableMap<String, Feature> = mutableMapOf()
) : SimpleRepository<String, Feature>(backingMap)