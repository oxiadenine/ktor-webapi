plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    application
}

group = "com.github.samgarasx"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlinxDependency("coroutines-core"))
    implementation(kotlinxDependency("serialization-json"))

    implementation(ktorDependency("server-netty"))
    implementation(ktorDependency("server-content-negotiation"))
    implementation(ktorDependency("server-cors"))
    implementation(ktorDependency("server-status-pages"))
    implementation(ktorDependency("serialization-kotlinx-json"))

    implementation(exposedDependency("core"))
    implementation(exposedDependency("jdbc"))

    implementation(hikaricpDependency())
    implementation(h2Dependency())

    implementation(logbackDependency("classic"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    target.compilations.all {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

application {
    mainClass.set("MainKt")
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = application.mainClass.get()
    }

    from(configurations["runtimeClasspath"].map { file: File ->
        if (file.isDirectory) file else zipTree(file)
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}