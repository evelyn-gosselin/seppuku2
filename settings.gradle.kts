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

// TODO: figure out dependencyResolutionManagement

include(
    "seppuku-client",
    "seppuku-client-components",

    "seppuku-plugins:heads-up-display",

    "seppuku-sdk:components",
    "seppuku-sdk:dependency-injection",
    "seppuku-sdk:feature-system",
    "seppuku-sdk:repository",
)
