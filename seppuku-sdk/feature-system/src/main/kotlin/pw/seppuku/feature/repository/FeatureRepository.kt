package pw.seppuku.feature.repository

import pw.seppuku.feature.Feature
import pw.seppuku.repository.repositories.SimpleRepository

abstract class FeatureRepository : SimpleRepository<String, Feature>()