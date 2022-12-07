plugins {
    java
    id("fabric-loom")
}

version = "0.1.0"

dependencies {
    minecraft("com.mojang:minecraft:1.19.2")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.14.11")

    implementation(project(":seppuku-metadata-api"))
    implementation(project(":seppuku-plugin-api"))
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }
}