package dev.slne.surf.elytra.listeners

import dev.slne.surf.elytra.recipes.items.ascendedWingCoreKey
import dev.slne.surf.elytra.recipes.items.ultimateWingCoreKey
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType

object WingCoreListener : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val item = event.item ?: return

        if (item.type != Material.WIND_CHARGE) {
            return
        }

        if (!(item.persistentDataContainer.has(
                ultimateWingCoreKey,
                PersistentDataType.BOOLEAN
            ) || item.persistentDataContainer.has(
                ascendedWingCoreKey,
                PersistentDataType.BOOLEAN
            ))
        ) {
            return
        }

        event.isCancelled = true
    }

}
