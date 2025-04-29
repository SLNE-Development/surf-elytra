package dev.slne.surf.elytra.commands.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.multiLiteralArgument
import dev.jorel.commandapi.kotlindsl.subcommand
import dev.slne.surf.elytra.recipes.items.MobItemDrop
import dev.slne.surf.elytra.utils.ElytraPermissionRegistry

fun CommandAPICommand.elytraGiveMobDropCommand() = subcommand("mob") {
    withPermission(ElytraPermissionRegistry.ELYTRA_COMMAND_GIVE)

    multiLiteralArgument("item", *MobItemDrop.entries.map { it.name }.toTypedArray())
}