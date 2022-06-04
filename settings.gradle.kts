pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven(
            // Only required if using EAP releases
            url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap"
        )
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.6.21")
            }
        }
    }
}

rootProject.name = "ktor-webapi"
