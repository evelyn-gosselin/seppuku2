plugins {
    kotlin("jvm")
    id("fabric-loom")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    minecraft("com.mojang:minecraft:1.19.3")
    mappings("net.fabricmc:yarn:1.19.3+build.2:v2")
    modImplementation("net.fabricmc:fabric-loader:0.14.11")

    implementation(project(":seppuku-client-access", configuration = "namedElements"))
    implementation(project(":seppuku-client-components", configuration = "namedElements"))

    implementation(project(":seppuku-sdk:components"))
    implementation(project(":seppuku-sdk:dependency-injection"))
    implementation(project(":seppuku-sdk:feature-system"))
    implementation(project(":seppuku-sdk:repository"))
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(mapOf("version" to project.version))
        }
    }
}