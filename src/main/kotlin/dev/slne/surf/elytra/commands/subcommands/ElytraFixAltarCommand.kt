package dev.slne.surf.elytra.commands.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.elytra.altar.AltarManager.initBrewingStand
import dev.slne.surf.elytra.utils.AltarItemFrame
import dev.slne.surf.elytra.utils.ElytraPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun CommandAPICommand.elytraFixAltarCommand() = subcommand("fix-altar") {
    withPermission(ElytraPermissionRegistry.ELYTRA_COMMAND_FIX_ALTAR)

    playerExecutor { player, args ->
        initBrewingStand()
        AltarItemFrame.initAll()

        player.sendText {
            appendPrefix()

            success("Altar wurde repariert.")
        }
    }
}