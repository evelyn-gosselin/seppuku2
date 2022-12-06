plugins {
    java
    id("fabric-loom")
}

version = "0.1.0"

dependencies {
    minecraft("com.mojang:minecraft:1.19.2")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:0.14.11")

    implementation(project(":seppuku-events", configuration = "namedElements"))

    implementation(project(":seppuku-metadata-api"))
    implementation(project(":seppuku-event-api"))
    implementation(project(":seppuku-feature-api"))
    implementation(project(":seppuku-plugin-api"))
}
