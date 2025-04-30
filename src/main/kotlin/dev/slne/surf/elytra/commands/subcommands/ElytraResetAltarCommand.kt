package dev.slne.surf.elytra.commands.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.elytra.altar.AltarManager
import dev.slne.surf.elytra.utils.ElytraPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun CommandAPICommand.elytraResetAltarCommand() = subcommand("reset-altar") {
    withPermission(ElytraPermissionRegistry.ELYTRA_COMMAND_RESET_ALTAR)

    playerExecutor { player, args ->
        AltarManager.altarAnimator?.stopAnimation()
        AltarManager.altarAnimator = null

        player.sendText {
            appendPrefix()

            success("Altar erfolgreich zurückgesetzt.")
        }
    }
}