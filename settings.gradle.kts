rootProject.name = "seppuku2"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven("https://maven.fabricmc.net")
    }

    plugins {
        java apply false
        id("fabric-loom") version "1.0-SNAPSHOT" apply false
    }
}

include(
    "seppuku-client",
    "seppuku-feature-api",
    "seppuku-metadata-api"
)