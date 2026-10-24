plugins {
    id("dev.kikugie.loom-back-compat")
    id("herobot.java")
    id("herobot.shade")
    `maven-publish`
}

description = "HeroBot for Fabric."

val minecraftVersion = sc.current.version
val isUnobfuscated = sc.current.parsed >= "26"

version = extra["artifactVersion"] as String

base {
    archivesName = "herobot-fabric"
}

loom {
    splitEnvironmentSourceSets()

    mods {
        register("herobot") {
            sourceSet(sourceSets.main.get())
            sourceSet(sourceSets["client"])
        }
    }

    runConfigs.all {
        generateRunConfig = sc.current.isActive
    }
}

repositories {
    maven("https://api.modrinth.com/maven") { name = "Modrinth" }
    maven("https://maven.fabricmc.net/") { name = "Fabric" }
}

// What gets bundled into the mod jar: `common` plus everything it exposes as `api`
// (pathetic). Transitive, unlike the rest of the runtime classpath, which is Minecraft
// and the mod dependencies and must stay out of the jar.
val shade = configurations.dependencyScope("shade")
val shadeClasspath = configurations.resolvable("shadeClasspath") {
    extendsFrom(shade.get())
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    if (isUnobfuscated) loomx.applyMojangMappings()
    else mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:${sc.properties.get<String>("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${sc.properties.get<String>("deps.fabric_api")}")
    modCompileOnly("maven.modrinth:carpet:${sc.properties.get<String>("deps.carpet")}")

    compileOnly(libs.voicechat.api)
    "clientCompileOnly"(libs.voicechat.api)

    implementation(project(":herobot-common"))
    add(shade.name, project(":herobot-common"))
}

tasks {
    processResources {
        val props = mapOf(
            "version" to project.version,
            "minecraft_version" to sc.properties.get<String>("mod.mc_compat"),
            "loader_version" to sc.properties.get<String>("deps.fabric_loader"),
        )

        inputs.properties(props)
        filteringCharset = "UTF-8"

        filesMatching("fabric.mod.json") {
            expand(props)
        }
    }

    shadowJar {
        configurations = listOf(shadeClasspath.get())

        // Loom adds the client source set to `jar`, not to shadowJar.
        from(sourceSets["client"].output)

        from(rootProject.file("LICENSE.txt")) {
            rename { "${it}_${base.archivesName.get()}" }
        }

        if (isUnobfuscated) {
            archiveClassifier = ""
        } else {
            archiveClassifier = "dev-shadow"
            destinationDirectory = layout.buildDirectory.dir("devlibs")
        }
    }

    if (isUnobfuscated) {
        jar {
            archiveClassifier = "thin"
            destinationDirectory = layout.buildDirectory.dir("devlibs")
        }
    } else {
        named<net.fabricmc.loom.task.RemapJarTask>("remapJar") {
            inputFile = shadowJar.flatMap { it.archiveFile }
        }
    }

    assemble {
        dependsOn(shadowJar)
    }
}

java {
    // Loom attaches sourcesJar to a RemapSourcesJar task and to `build` when present.
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }
}
