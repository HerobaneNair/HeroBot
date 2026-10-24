import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    alias(libs.plugins.fabric.loom) apply false
    alias(libs.plugins.fabric.loom.remap) apply false
    alias(libs.plugins.paperweight.userdev) apply false
}

description = "Robots scripted to be Heroes"

// One instant for the whole build, so the jars and the generation folder holding them can never
// disagree about the date. Nodes read `buildDate` back through `herobot.java`.
val buildTime: LocalDateTime = LocalDateTime.now()

extra["buildDate"] = buildTime.format(DateTimeFormatter.ofPattern("yyMMdd"))
val generation = "$version+v${buildTime.format(DateTimeFormatter.ofPattern("yyMMdd_HHmm"))}"

tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}

val releaseNodes = listOf(":herobot-mod", ":herobot-plugin").flatMap { project(it).subprojects }
releaseNodes.forEach { evaluationDependsOn(it.path) }

tasks.register<Sync>("buildAll") {
    group = "herobot"
    description = "Builds the Fabric mod and the Paper plugin for every Minecraft version into a build/libs generation folder."

    for (node in releaseNodes) {
        from(node.tasks.named(if ("remapJar" in node.tasks.names) "remapJar" else "shadowJar"))
    }
    into(layout.buildDirectory.dir("libs/$generation"))

    doLast {
        logger.lifecycle("Built into ${destinationDir}:")
        destinationDir.listFiles()?.sorted()?.forEach { logger.lifecycle("  ${it.name}") }
    }
}
