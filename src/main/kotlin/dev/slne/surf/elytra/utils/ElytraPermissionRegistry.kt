package dev.slne.surf.elytra.utils

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object ElytraPermissionRegistry : PermissionRegistry() {

    val ELYTRA_COMMAND = create("surf.elytra.command")
    val ELYTRA_COMMAND_GIVE = create("surf.elytra.command.give")
}