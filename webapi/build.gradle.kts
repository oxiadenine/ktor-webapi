import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.kotlin.jvm")
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.flywaydb.flyway") version "5.2.4"
}

val ktor_version: String   by rootProject
val kotlin_Exposed_version: String   by rootProject
val sqliteJdbc_version: String  by rootProject
val logback_version: String   by rootProject

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
    val flyway_version: String by project
    implementation(kotlin("stdlib-jdk8"))
    //Ref  : https://ktor.io/docs/migrating-2.html#server-package
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
    implementation("io.ktor:ktor-server-auto-head-response:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("io.ktor:ktor-server-call-id:$ktor_version")
    implementation("io.ktor:ktor-server-double-receive:$ktor_version")
    implementation("io.ktor:ktor-server-data-conversion:$ktor_version")
    implementation("io.ktor:ktor-server-default-headers:$ktor_version")
    implementation("io.ktor:ktor-server-compression:$ktor_version")
    implementation("io.ktor:ktor-server-caching-headers:$ktor_version")
    implementation("io.ktor:ktor-server-conditional-headers:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("io.ktor:ktor-server-hsts:$ktor_version")
    implementation("io.ktor:ktor-server-http-redirect:$ktor_version")
    implementation("io.ktor:ktor-server-partial-content:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")

    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("org.jetbrains.exposed:exposed-core:$kotlin_Exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$kotlin_Exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$kotlin_Exposed_version")
    implementation("org.xerial:sqlite-jdbc:$sqliteJdbc_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.flywaydb:flyway-core:$flyway_version")
}
