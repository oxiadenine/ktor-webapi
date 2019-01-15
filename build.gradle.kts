import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
    application
    id("org.flywaydb.flyway") version "5.2.4"
}

group = "samgarasx"
version = "0.1.0"

val ktorVersion: String by project
val kotlinExposedVersion: String by project
val sqliteJdbcVersion: String by project
val logbackVersion: String by project
val flywayVersion: String by project

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("org.jetbrains.exposed:exposed:$kotlinExposedVersion")
    compile("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
}

application {
    mainClassName = "ktorwebapi.ServerKt"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

flyway {
    val flywayEnvironment: String by project

    configFiles = arrayOf("database/configuration/database.$flywayEnvironment.conf")
    locations = arrayOf("filesystem:database/migrations")
}
