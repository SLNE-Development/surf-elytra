package dev.slne.surf.elytra.commands.subcommands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.elytra.recipes.items.MobItemDrop
import dev.slne.surf.elytra.utils.ElytraPermissionRegistry
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.entity.Player

fun CommandAPICommand.elytraGiveMobDropCommand() = subcommand("give-mob-drop") {
    withPermission(ElytraPermissionRegistry.ELYTRA_COMMAND_GIVE)

    entitySelectorArgumentManyPlayers("targets", allowEmpty = false)
    multiLiteralArgument("item", *MobItemDrop.entries.map { it.name }.toTypedArray())
    integerArgument("amount", 1, 64)

    playerExecutor { player, args ->
        val targets: List<Player> by args
        val item: String by args
        val amount: Int by args

        val itemDrop =
            MobItemDrop.entries.find { it.name == item } ?: return@playerExecutor player.sendText {
                appendPrefix()

                error("Item $item nicht gefunden.")
            }

        val itemStack = itemDrop.itemStack.clone()
        itemStack.amount = amount

        targets.forEach { target ->
            target.inventory.addItem(itemStack)
            target.sendText {
                appendPrefix()

                success("Du hast ${itemDrop.name} erhalten.")
            }
        }

        player.sendText {
            appendPrefix()

            success("Du hast ${itemDrop.name} an [${targets.joinToString(", ") { it.name }}] gegeben.")
        }
    }
}