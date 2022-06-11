import org.gradle.api.Project

fun Project.kotlinxDependency(name: String) =
    "org.jetbrains.kotlinx:kotlinx-$name:${kotlinxVersion(name.substringBefore("-"))}"

fun Project.ktorDependency(name: String) = "io.ktor:ktor-$name:${version("ktor")}"
fun Project.exposedDependency(name: String) = "org.jetbrains.exposed:exposed-$name:${version("exposed")}"
fun Project.hikaricpDependency() = "com.zaxxer:HikariCP:${version("hikaricp")}"
fun Project.h2Dependency() = "com.h2database:h2:${version("h2")}"
fun Project.logbackDependency(name: String) = "ch.qos.logback:logback-$name:${version("logback")}"