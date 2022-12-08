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
    "seppuku-client",
    "seppuku-client-access",
    "seppuku-client-components",

    "seppuku-plugins:auto-tool",
    "seppuku-plugins:fast-mine",
    "seppuku-plugins:heads-up-display",
    "seppuku-plugins:sprint",
    "seppuku-plugins:vanilla-fly",

    "seppuku-sdk:components",
    "seppuku-sdk:dependency-injection",
    "seppuku-sdk:feature-system",
    "seppuku-sdk:repository",
)
