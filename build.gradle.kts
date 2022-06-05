val ktorVersion: String by project
val kotlinExposedVersion: String by project
val sqliteJdbcVersion: String by project
val logbackVersion: String by project
val flywayVersion: String by project

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}