import org.gradle.api.Project

fun Project.kotlinVersion() = version("kotlin")
fun Project.kotlinxVersion(name: String) = version("kotlinx-$name")

fun Project.version(target: String) = property("${target}.version") as String