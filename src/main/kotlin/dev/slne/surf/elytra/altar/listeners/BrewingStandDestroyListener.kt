package dev.slne.surf.elytra.altar.listeners

import dev.slne.surf.elytra.altar.AltarManager
import org.bukkit.block.BrewingStand
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

object BrewingStandDestroyListener : Listener {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val block = event.block
        val brewingStand = block.state as? BrewingStand ?: return

        if (AltarManager.isAltarBrewingStand(brewingStand)) {
            event.isCancelled = true
        }
    }

}