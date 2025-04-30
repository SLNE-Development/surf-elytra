package dev.slne.surf.elytra

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.elytra.altar.AltarManager
import dev.slne.surf.elytra.commands.elytraCommand
import dev.slne.surf.elytra.listeners.ListenerManager
import dev.slne.surf.elytra.recipes.registerRecipes
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(SurfElytra::class.java)

class SurfElytra : SuspendingJavaPlugin() {

    override suspend fun onLoadAsync() {

    }

    override suspend fun onEnableAsync() {
        ListenerManager.registerListeners()
        AltarManager.initAltar()

        registerRecipes()
        elytraCommand()
    }

    override suspend fun onDisableAsync() {

    }

}