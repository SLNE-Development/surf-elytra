package dev.slne.surf.elytra.listeners

import dev.slne.surf.elytra.recipes.items.MobItemDrop
import dev.slne.surf.surfapi.core.api.util.random
import org.bukkit.Material
import org.bukkit.block.data.type.Beehive
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

object HoneyListener : Listener {

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val block = event.clickedBlock ?: return
        val player = event.player

        if (event.action != Action.RIGHT_CLICK_BLOCK) {
            return
        }

        if (!(isShear(player.inventory.itemInMainHand) || isShear(player.inventory.itemInOffHand))) {
            return
        }

        if (!(block.type === Material.BEEHIVE || block.type === Material.BEE_NEST)) {
            return
        }

        val hiveBlockData = block.blockData as Beehive
        if (hiveBlockData.honeyLevel != hiveBlockData.maximumHoneyLevel) {
            return
        }

        if (!(random.nextDouble(0.0, 100.0) <= MobItemDrop.NECTAR_HEART.probability)) {
            return
        }

        block.world.dropItem(block.location, MobItemDrop.NECTAR_HEART.itemStack.clone())
    }

    private fun isShear(itemStack: ItemStack): Boolean {
        return itemStack.type == Material.SHEARS
    }

}