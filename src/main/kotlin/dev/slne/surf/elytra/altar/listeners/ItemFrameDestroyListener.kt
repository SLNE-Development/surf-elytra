package dev.slne.surf.elytra.altar.listeners

import dev.slne.surf.elytra.utils.AltarItemFrame
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.hanging.HangingBreakEvent

object ItemFrameDestroyListener : Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    fun onEntityRemove(event: HangingBreakEvent) {
        val itemFrame = event.entity as? ItemFrame ?: return

        if (AltarItemFrame.isAltarItemFrame(itemFrame)) {
            event.isCancelled = true
        }
    }

}