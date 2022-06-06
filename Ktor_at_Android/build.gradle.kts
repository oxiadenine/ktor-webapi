plugins {
    id("com.android.application")
    kotlin("android")
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("org.flywaydb.flyway") version "5.2.4"
}

android {
    compileSdkVersion(30)
    buildFeatures {
        viewBinding = true
    }
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        allWarningsAsErrors = true
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude("META-INF/*")
    }
}

flyway {
    configFiles = arrayOf("db/flyway.conf")
    locations = arrayOf("filesystem:db/migrations")
}

dependencies {
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    val ktor_version: String by project
    val kotlin_Exposed_version: String by project
    val sqliteJdbc_version: String by project
    val logback_version: String by project
    val flyway_version: String by project
    val kotlin_coroutines_version: String by project

    implementation(kotlin("stdlib-jdk8"))
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")

    implementation ("androidx.core:core-ktx:1.6.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.5")

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

    implementation(project(":ktor_unit"))
}
