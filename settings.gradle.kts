pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
        maven("https://repo.papermc.io/repository/maven-public/") { name = "PaperMC" }
        mavenCentral()
        gradlePluginPortal()
    }

    includeBuild("build-logic")
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.8"
    id("dev.kikugie.loom-back-compat") version "0.4.3"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "HeroBot"

include(":herobot-common")

val minecraftVersions = listOf("1.21.11", "26.1.2", "26.2", "26.3")

stonecutter {
    create("herobot-mod") {
        versions(minecraftVersions)
        vcsVersion = "1.21.11"
    }

    create("herobot-plugin") {
        versions(minecraftVersions)
        vcsVersion = "1.21.11"
    }
}
