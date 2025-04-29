package dev.slne.surf.elytra.commands.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.entitySelectorArgumentManyPlayers
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.elytra.utils.ElytraPermissionRegistry

fun CommandAPICommand.elytraGiveCommand() = subcommand("give") {
    withPermission(ElytraPermissionRegistry.ELYTRA_COMMAND_GIVE)

    entitySelectorArgumentManyPlayers("targets", allowEmpty = false)

    
}