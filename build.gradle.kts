import dev.slne.surf.surfapi.gradle.util.withSurfApiBukkit
import xyz.jpenilla.runpaper.task.RunServer

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.elytra.SurfElytra")
    authors.add("Ammo")

    generateLibraryLoader(false)
}

tasks.withType<RunServer> {
    withSurfApiBukkit()
}
