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

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven(
            // Only required if using EAP releases
            url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap"
        )
    }
}

rootProject.name = "ktor-webapi"
