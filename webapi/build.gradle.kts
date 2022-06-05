import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {//FIXME How move to dependencyResolutionManagement at Setting.gradle.kts
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
    gradlePluginPortal()
}

plugins {
//    kotlin("jvm") version "1.6.21"
    id("org.jetbrains.kotlin.jvm") version "1.6.21"
    application
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.flywaydb.flyway") version "5.2.4"
}

//val ktorVersion: String by project//FIXME
//val kotlinExposedVersion: String by project//FIXME
//val sqliteJdbcVersion: String by project//FIXME
//val logbackVersion: String by project//FIXME
//val flywayVersion: String by project//FIXME
val ktorVersion: String ="2.0.2"
val kotlinExposedVersion: String ="0.38.2"
val sqliteJdbcVersion: String="3.27.2"
val logbackVersion: String ="1.2.3"
val flywayVersion: String ="5.2.4"

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
