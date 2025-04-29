package dev.slne.surf.elytra.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.slne.surf.elytra.utils.ElytraPermissionRegistry

fun elytraCommand() = commandAPICommand("elytra") {
    withPermission(ElytraPermissionRegistry.ELYTRA_COMMAND)
}