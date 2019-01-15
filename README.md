## Introduction

This project is a an example of [Ktor](https://ktor.io/) web framework for a Web API.

The database engine used by the project is [SQLite](https://www.sqlite.org/).

### Getting Started

To create and migrate the database:

1. Initialize database with `./gradlew flywayInfo`
2. Migrate database with `./gradlew flywayMigrate -i`

To start the Ktor server:

1. Install dependencies with `./gradlew`
2. Build executables with `./gradlew build`
3. Start Ktor server with `./gradlew run`

Now you can visit http://localhost:3000/api from your browser.
