package dev.slne.surf.elytra.listeners

import dev.slne.surf.elytra.recipes.items.MobItemDrop
import dev.slne.surf.surfapi.core.api.util.random
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent

object MobKillListener : Listener {

    @EventHandler
    fun onEntityDeath(event: EntityDeathEvent) {
        val entity = event.entity
        val killer = event.entity.killer

        if (killer !is Player) {
            return
        }

        val itemDrops = MobItemDrop.getItemDropsByEntityType(entity.type)
        if (itemDrops.isEmpty()) {
            return
        }

        val randomDouble = random.nextDouble(0.0, 100.0)

        itemDrops.forEach { itemDrop ->
            if (randomDouble <= itemDrop.probability) {
                event.drops.add(itemDrop.itemStack.clone())
            }
        }
    }

}