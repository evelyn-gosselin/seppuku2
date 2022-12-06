plugins {
    java
}

version = "0.1.0"

dependencies {
    implementation(project(":seppuku-metadata-api"))
    implementation(project(":seppuku-event-api"))
    implementation(project(":seppuku-feature-api"))
}