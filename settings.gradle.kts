pluginManagement {
    repositories {
        gradlePluginPortal()
        jcenter()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin")) {
                useVersion("1.3.31")
            }
        }
    }
}

rootProject.name = "ktor-webapi"
