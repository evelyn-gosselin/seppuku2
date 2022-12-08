package pw.seppuku.client.feature.features

import pw.seppuku.client.components.client.MinecraftClientInit
import pw.seppuku.components.HumanIdentifier
import pw.seppuku.di.DependencyInjector
import pw.seppuku.feature.AbstractFeature
import pw.seppuku.feature.Component
import pw.seppuku.feature.Feature
import pw.seppuku.feature.findComponent
import pw.seppuku.feature.repository.FeatureRepository
import java.io.File
import java.net.URLClassLoader
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import kotlin.reflect.KClass

class PluginLoaderFeature(
    private val dependencyInjector: DependencyInjector,
    private val featureRepository: FeatureRepository
) : AbstractFeature() {

    @Component
    private val humanIdentifier = HumanIdentifier("external_feature_loader")

    @Component
    private val minecraftClientInit = MinecraftClientInit {
        loadPluginFeatures()
    }

    private fun loadPluginFeatures() {
        DISCOVERY_PATHS
            .filter(File::exists)
            .map(File::walkTopDown)
            .map(FileTreeWalk::toList)
            .flatten()
            .filter(File::isFile)
            .filter(this::isZipFile)
            .map(this::loadPluginClasses)
            .flatten()
            .mapNotNull(this::featureClassOrNull)
            .map(this::createFeatureInstance)
            .forEach(this::saveFeatureInstance)
    }

    private fun isZipFile(pluginFile: File): Boolean =
        pluginFile.extension == "jar" || pluginFile.extension == "zip"

    private fun loadPluginClasses(pluginFile: File): List<KClass<out Any>> {
        val pluginZipFile = ZipFile(pluginFile)
        if (!hasIdentifierZipEntry(pluginZipFile))
            return emptyList()

        val pluginFileUrl = pluginFile.toURI().toURL()
        val pluginClassLoader = URLClassLoader(arrayOf(pluginFileUrl), javaClass.classLoader)

        return toZipEntries(pluginZipFile)
            .filter(this::isClassZipEntry)
            .map {
                val pluginClassName = it.name.substring(0, it.name.length - 6).replace("/", ".")
                pluginClassLoader.loadClass(pluginClassName).kotlin
            }
    }

    @Suppress("UNCHECKED_CAST")
    private fun featureClassOrNull(pluginClass: KClass<out Any>): KClass<out Feature>? =
        if (Feature::class.java.isAssignableFrom(pluginClass.java))
            pluginClass as KClass<out Feature>
        else
            null

    private fun createFeatureInstance(pluginClass: KClass<out Feature>): Feature =
        dependencyInjector.create(pluginClass)

    private fun saveFeatureInstance(feature: Feature) {
        val humanIdentifier = feature.findComponent<HumanIdentifier>()
        featureRepository.save(humanIdentifier.toString(), feature)
    }

    private fun hasIdentifierZipEntry(zipFile: ZipFile): Boolean =
        zipFile.getEntry("seppuku.json") != null

    private fun toZipEntries(zipFile: ZipFile): List<ZipEntry> =
        zipFile.entries().toList()

    private fun isClassZipEntry(zipEntry: ZipEntry): Boolean =
        zipEntry.name.endsWith(".class")

    companion object {
        private val DISCOVERY_PATHS = sequenceOf(
            File("seppuku/plugins/"),
            File("mods/seppuku/plugins/"),
        )
    }
}