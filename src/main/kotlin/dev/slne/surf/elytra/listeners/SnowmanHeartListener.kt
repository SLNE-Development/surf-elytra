package dev.slne.surf.elytra.listeners

import dev.slne.surf.elytra.recipes.items.snowmanHeartKey
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.persistence.PersistentDataType

object SnowmanHeartListener : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val item = event.item ?: return

        if (item.type != Material.SNOWBALL) {
            return
        }

        if (!(item.persistentDataContainer.has(
                snowmanHeartKey,
                PersistentDataType.BOOLEAN
            ))
        ) {
            return
        }

        event.isCancelled = true
    }

}
