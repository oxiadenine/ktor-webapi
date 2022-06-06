import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.kotlin.jvm")  
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.flywaydb.flyway") version "5.2.4"
}

val ktorVersion: String ? by rootProject
val kotlinExposedVersion: String ? by rootProject
val sqliteJdbcVersion: String? by rootProject
val logbackVersion: String ? by rootProject

group = "samgarasx"
version = "0.1.0"

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
    val flywayVersion: String by project
    implementation(kotlin("stdlib-jdk8"))
    //Ref  : https://ktor.io/docs/migrating-2.html#server-package
    implementation("io.ktor:ktor-server-call-logging:$ktorVersion")
    implementation("io.ktor:ktor-server-auto-head-response:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-server-call-id:$ktorVersion")
    implementation("io.ktor:ktor-server-double-receive:$ktorVersion")
    implementation("io.ktor:ktor-server-data-conversion:$ktorVersion")
    implementation("io.ktor:ktor-server-default-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-compression:$ktorVersion")
    implementation("io.ktor:ktor-server-caching-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-conditional-headers:$ktorVersion")
    implementation("io.ktor:ktor-server-cors:$ktorVersion")
    implementation("io.ktor:ktor-server-hsts:$ktorVersion")
    implementation("io.ktor:ktor-server-http-redirect:$ktorVersion")
    implementation("io.ktor:ktor-server-partial-content:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions:$ktorVersion")

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-gson:$ktorVersion")
    implementation("org.jetbrains.exposed:exposed-core:$kotlinExposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$kotlinExposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$kotlinExposedVersion")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbcVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("org.flywaydb:flyway-core:$flywayVersion")
}
