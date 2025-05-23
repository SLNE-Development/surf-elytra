package dev.slne.surf.elytra.commands

import dev.jorel.commandapi.kotlindsl.commandAPICommand
import dev.slne.surf.elytra.commands.subcommands.elytraFixAltarCommand
import dev.slne.surf.elytra.commands.subcommands.elytraGiveElytraItemCommand
import dev.slne.surf.elytra.commands.subcommands.elytraGiveMobDropCommand
import dev.slne.surf.elytra.commands.subcommands.elytraResetAltarCommand
import dev.slne.surf.elytra.utils.ElytraPermissionRegistry

fun elytraCommand() = commandAPICommand("elytra") {
    withPermission(ElytraPermissionRegistry.ELYTRA_COMMAND)

    elytraGiveMobDropCommand()
    elytraGiveElytraItemCommand()
    elytraResetAltarCommand()
    elytraFixAltarCommand()
    elytraResetAltarCommand()
}