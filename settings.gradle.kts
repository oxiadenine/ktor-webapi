pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
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
