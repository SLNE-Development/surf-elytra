package dev.slne.surf.elytra.listeners

import dev.slne.surf.elytra.recipes.items.brokenCelestialGliderKey
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.enchantment.PrepareItemEnchantEvent
import org.bukkit.event.inventory.PrepareAnvilEvent
import org.bukkit.event.inventory.PrepareGrindstoneEvent
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.player.PlayerItemMendEvent
import org.bukkit.persistence.PersistentDataType

object CelestialGliderListener : Listener {

    @EventHandler
    fun onPlayerItemMend(event: PlayerItemMendEvent) {
        val item = event.item

        if (item.type != Material.ELYTRA) {
            return
        }

        if (!item.persistentDataContainer.has(
                brokenCelestialGliderKey,
                PersistentDataType.BOOLEAN
            )
        ) {
            return
        }

        event.isCancelled = true
    }

    @EventHandler
    fun onPrepareItemCraft(event: PrepareItemCraftEvent) {
        event.inventory.matrix.forEach { item ->
            if (item == null) {
                return@forEach
            }

            if (item.type != Material.ELYTRA) {
                return@forEach
            }

            if (!item.persistentDataContainer.has(
                    brokenCelestialGliderKey,
                    PersistentDataType.BOOLEAN
                )
            ) {
                return@forEach
            }

            event.inventory.result = null
        }
    }

    @EventHandler
    fun onPrepareAnvil(event: PrepareAnvilEvent) {
        val firstItem = event.inventory.firstItem
        val secondItem = event.inventory.secondItem

        if (firstItem == null || secondItem == null) {
            return
        }

        if (firstItem.type != Material.ELYTRA && secondItem.type != Material.ELYTRA) {
            return
        }

        if (!(firstItem.persistentDataContainer.has(
                brokenCelestialGliderKey,
                PersistentDataType.BOOLEAN
            ) || secondItem.persistentDataContainer.has(
                brokenCelestialGliderKey,
                PersistentDataType.BOOLEAN
            ))
        ) {
            return
        }

        event.result = null
    }

    @EventHandler(ignoreCancelled = true)
    fun onPrepareGrindstone(event: PrepareGrindstoneEvent) {
        val firstItem = event.inventory.upperItem
        val secondItem = event.inventory.lowerItem

        if (firstItem == null || secondItem == null) {
            return
        }

        if (firstItem.type != Material.ELYTRA && secondItem.type != Material.ELYTRA) {
            return
        }

        if (!(firstItem.persistentDataContainer.has(
                brokenCelestialGliderKey,
                PersistentDataType.BOOLEAN
            ) || secondItem.persistentDataContainer.has(
                brokenCelestialGliderKey,
                PersistentDataType.BOOLEAN
            ))
        ) {
            return
        }

        event.result = null
    }

    @EventHandler(ignoreCancelled = true)
    fun onPrepareItemEnchant(event: PrepareItemEnchantEvent) {
        val item = event.item

        if (item.type != Material.ELYTRA) {
            return
        }

        if (!item.persistentDataContainer.has(
                brokenCelestialGliderKey,
                PersistentDataType.BOOLEAN
            )
        ) {
            return
        }

        event.isCancelled = true
    }
    
}