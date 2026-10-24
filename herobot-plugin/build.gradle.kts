import net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission

plugins {
    id("herobot.java")
    id("herobot.shade")
    id("herobot.paper-plugin")
    id("io.papermc.paperweight.userdev")
}

description = "HeroBot for Paper."

// Not `version`: paper-plugin.yml wants the plain plugin version, the jar name wants the stamp.
val artifactVersion = extra["artifactVersion"] as String

base {
    archivesName = "herobot-paper"
}

dependencies {
    paperweight.paperDevBundle(sc.properties.get<String>("deps.paper"))

    implementation(project(":herobot-common"))

    compileOnly(libs.voicechat.api)
}

paper {
    main = "hero.bane.herobot.paper.HeroBotPlugin"

    serverDependencies {
        register("voicechat") {
            required = false
            load = net.minecrell.pluginyml.paper.PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
    }

    permissions {
        register("herobot.ai.edit") {
            description = "Save and delete HeroScripts from the in-game editor"
            default = Permission.Default.OP
        }
    }
}

tasks.shadowJar {
    archiveClassifier = ""
    archiveVersion = artifactVersion
}
