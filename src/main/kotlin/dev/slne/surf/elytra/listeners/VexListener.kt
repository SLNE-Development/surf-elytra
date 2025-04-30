package dev.slne.surf.elytra.listeners

import com.github.shynixn.mccoroutine.folia.ticks
import dev.slne.surf.surfapi.core.api.messages.adventure.playSound
import dev.slne.surf.surfapi.core.api.util.mutableObjectListOf
import kotlinx.coroutines.delay
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Allay
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*
import kotlin.time.Duration.Companion.seconds

object VexListener : Listener {

    private val conversions = mutableObjectListOf<UUID>()

    @EventHandler
    suspend fun onPlayerInteractAtEntity(event: PlayerInteractAtEntityEvent) {
        val allay = event.rightClicked as? Allay ?: return

        if (!allay.hasPotionEffect(PotionEffectType.INFESTED)) {
            return
        }

        val clickedWithItem = event.player.equipment.getItem(event.hand)

        if (clickedWithItem.type != Material.GRAY_DYE) {
            return
        }

        if (conversions.contains(allay.uniqueId)) {
            return
        }

        if (event.player.gameMode == GameMode.SURVIVAL || event.player.gameMode == GameMode.ADVENTURE) {
            if (clickedWithItem.amount > 1) {
                clickedWithItem.amount--
            } else {
                event.player.equipment.setItem(event.hand, null)
            }
        }

        conversions.add(allay.uniqueId)

        allay.setAI(false)
        allay.isGlowing = true

        allay.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 5 * 20, 3, false, false))

        val delaySeconds = 5
        val delayBetweenTicks = 5
        val repeat = delaySeconds * 20 / delayBetweenTicks

        repeat(repeat) {
            playEatSound(allay)
            delay(delayBetweenTicks.ticks)
            allay.world.spawnParticle(Particle.ANGRY_VILLAGER, allay.location, 1)
        }
        
        val location = allay.location.clone()
        allay.remove()
        conversions.remove(allay.uniqueId)

        location.world.spawnEntity(location, EntityType.VEX)

        location.world.getNearbyPlayers(location, 30.0).forEach { player ->
            player.playSound {
                type(Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED)
                volume(.5f)
            }

            delay(1.seconds)

            player.playSound {
                type(Sound.ENTITY_VEX_CHARGE)
                volume(.5f)
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