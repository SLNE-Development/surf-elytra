package dev.slne.surf.elytra.altar.listeners

import com.github.shynixn.mccoroutine.folia.launch
import dev.slne.surf.elytra.altar.AltarManager
import dev.slne.surf.elytra.plugin
import dev.slne.surf.surfapi.bukkit.api.util.key
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import org.bukkit.block.BrewingStand
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType

object BrewingStandClickListener : Listener {

    private fun getPlayerLevelsKey(player: Player) = key("altar_player_levels_${player.uniqueId}")

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val block = event.clickedBlock ?: return
        val brewingStand = block.state as? BrewingStand ?: return

        if (!AltarManager.isAltarBrewingStand(brewingStand)) {
            return
        }

        if (AltarManager.isAltarAnimationRunning()) {
            event.isCancelled = true

            event.player.sendText {
                appendPrefix()

                error("Aktuell läuft eine Animation. Solange diese läuft, kannst du keine weiteren Level hinzufügen")
            }

            return
        }

        event.isCancelled = true

        val player = event.player

        if (!player.isSneaking) {
            val levels = getAltarLevels(player, brewingStand)

            player.sendText {
                appendPrefix()

                info("Du hast aktuell ")
                variableValue("$levels/${AltarManager.MAX_ALTAR_LEVEL}")
                info(" Level in den Altar eingezahlt.")
            }

            return
        }

        if (event.action == Action.RIGHT_CLICK_BLOCK) {
            val levels = getAltarLevels(player, brewingStand)

            if (levels >= AltarManager.MAX_ALTAR_LEVEL) {
                player.sendText {
                    appendPrefix()

                    error("Der Altar ist bereits auf das Maximum aufgeladen.")
                }
                return
            }

            val playerLevels = player.level

            if (playerLevels == 0) {
                player.sendText {
                    appendPrefix()

                    error("Du hast aktuell keine Level, die du in den Altar einzahlen könntest.")
                }
                return
            }

            val missingLevels = AltarManager.MAX_ALTAR_LEVEL - levels
            val toFill = if (playerLevels >= missingLevels) {
                missingLevels
            } else {
                playerLevels
            }

            player.giveExpLevels(-toFill)
            setAltarLevels(player, brewingStand, levels + toFill)

            player.sendText {
                appendPrefix()

                success("Du hast ")
                variableValue("$toFill/${AltarManager.MAX_ALTAR_LEVEL}")
                success(" Level in den Altar eingezahlt.")
            }

            plugin.launch {
                AltarManager.checkState(player)
            }
        } else if (event.action == Action.LEFT_CLICK_BLOCK) {
            val levels = getAltarLevels(player, brewingStand)

            if (levels <= 0) {
                player.sendText {
                    appendPrefix()

                    error("Du hast aktuell keine Level in den Altar eingezahlt.")
                }

                return
            }

            player.giveExpLevels(levels)
            setAltarLevels(player, brewingStand, 0)

            player.sendText {
                appendPrefix()

                success("Du hast ")
                variableValue(levels)
                success(" Level aus dem Altar entnommen.")
            }
        }
    }

    fun setAltarLevels(player: Player, brewingStand: BrewingStand, levels: Int) {
        brewingStand.persistentDataContainer.set(
            getPlayerLevelsKey(player),
            PersistentDataType.INTEGER,
            levels
        )
        brewingStand.update()
    }

    fun getAltarLevels(player: Player, brewingStand: BrewingStand) =
        brewingStand.persistentDataContainer.get(
            getPlayerLevelsKey(player),
            PersistentDataType.INTEGER
        ) ?: 0

}