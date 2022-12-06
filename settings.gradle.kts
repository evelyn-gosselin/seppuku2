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

    "seppuku-events",

    "seppuku-metadata-api",
    "seppuku-event-api",
    "seppuku-feature-api",
    "seppuku-mixins-api",
    "seppuku-plugin-api",

    "plugins:seppuku-heads-up",
    "plugins:seppuku-sprint",
)