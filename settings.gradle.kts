rootProject.name = "seppuku2"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven("https://maven.fabricmc.net")
    }

    plugins {
        kotlin("jvm") version "1.7.21" apply false
        id("fabric-loom") version "1.0-SNAPSHOT" apply false
    }
}

include(
    "seppuku-sdk:dependency-injection",
    "seppuku-sdk:feature-system",
)
