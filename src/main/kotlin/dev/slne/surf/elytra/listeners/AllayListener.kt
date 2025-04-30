package dev.slne.surf.elytra.listeners

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.globalRegionDispatcher
import com.github.shynixn.mccoroutine.folia.launch
import com.github.shynixn.mccoroutine.folia.ticks
import dev.slne.surf.elytra.plugin
import dev.slne.surf.elytra.recipes.items.allayFoodItemStack
import dev.slne.surf.surfapi.core.api.messages.adventure.playSound
import dev.slne.surf.surfapi.core.api.util.mutableObjectListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Allay
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import java.util.*

object AllayListener : Listener {

    private val mating = mutableObjectListOf<UUID>()

    @EventHandler
    fun onPlayerInteractAtEntity(event: PlayerInteractAtEntityEvent) {
        val allay = event.rightClicked as? Allay ?: return
        val clickedItem = event.player.inventory.getItem(event.hand)

        if (allay.hasActiveItem()) {
            return
        }

        if (mating.contains(allay.uniqueId)) {
            return
        }

        if (!clickedItem.isSimilar(allayFoodItemStack)) {
            return
        }

        mating.add(allay.uniqueId)

        plugin.launch(plugin.entityDispatcher(allay)) {
            allay.startDancing()

            val delaySeconds = 5
            val delayBetweenTicks = 5
            val repeat = delaySeconds * 20 / delayBetweenTicks

            repeat(repeat) {
                playEatSound(allay)
                delay(delayBetweenTicks.ticks)
                allay.world.spawnParticle(Particle.HAPPY_VILLAGER, allay.location, 1)
            }

            allay.world.spawnParticle(Particle.HEART, allay.location, 1)
            allay.stopDancing()

            allay.equipment.setItem(EquipmentSlot.HAND, ItemStack.empty())

            withContext(plugin.globalRegionDispatcher) {
                allay.world.spawnEntity(allay.location, EntityType.ALLAY)

                mating.remove(allay.uniqueId)
            }
        }
    }

    private fun playEatSound(allay: Allay) {
        allay.world.getNearbyPlayers(allay.location, 30.0).forEach { player ->
            player.playSound {
                type(Sound.ENTITY_GENERIC_EAT)
                volume(.5f)
            }
        }
    }

}