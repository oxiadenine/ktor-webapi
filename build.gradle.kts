import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "samgarasx"
version = "0.1.0"

plugins {
    kotlin("jvm")
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.flywaydb.flyway") version "5.2.4"
}

val ktorVersion: String by project
val kotlinExposedVersion: String by project
val sqliteJdbcVersion: String by project
val logbackVersion: String by project
val flywayVersion: String by project

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
    maven(
        // Only required if using EAP releases
        url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap"
    )
}

application {
    mainClassName = "ktorwebapi.ServerKt"
}

tasks.withType<ShadowJar> {
    setProperty("archiveBaseName", project.name)
    setProperty("archiveVersion", project.version)
    setProperty("archiveClassifier", null)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

flyway {
    configFiles = arrayOf("db/flyway.conf")
    locations = arrayOf("filesystem:db/migrations")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("org.jetbrains.exposed:exposed:$kotlinExposedVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
}
