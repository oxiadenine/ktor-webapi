pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven {
            url = uri("https://repo.maven.apache.org/maven2/")
        }
        maven {// Only required if using EAP releases
            url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenLocal()
    }

//    plugins {
//        id("org.jetbrains.kotlin.jvm") version "1.6.21"
//    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin") || requested.id.id == "org.jetbrains.kotlin" || requested.id.namespace == "org.jetbrains.kotlin" || requested.id.id == "kotlin-multiplatform" || requested.id.id == "org.jetbrains.kotlin.multiplatform" || requested.id.id == "kotlin-dce-js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
            }
        }
    }
}

//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
////    repositories {
////        mavenCentral()
////        google()
////        maven {
////            url = uri("https://repo.maven.apache.org/maven2/")
////        }
////        maven {// Only required if using EAP releases
////            url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
////        }
////        maven {
////            url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
////        }
////        maven { url = uri("https://plugins.gradle.org/m2/") }
////        mavenLocal()
////        gradlePluginPortal()
////    }
//}

rootProject.name = "ktor-webapi"
includeBuild("webapi")