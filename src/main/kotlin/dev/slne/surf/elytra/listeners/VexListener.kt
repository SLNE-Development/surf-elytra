package dev.slne.surf.elytra.listeners

import dev.slne.surf.surfapi.core.api.messages.adventure.playSound
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

object VexListener : Listener {

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

        if (event.player.gameMode == GameMode.SURVIVAL || event.player.gameMode == GameMode.ADVENTURE) {
            if (clickedWithItem.amount > 1) {
                clickedWithItem.amount--
            } else {
                event.player.equipment.setItem(event.hand, null)
            }
        }

        allay.setAI(false)
        allay.isGlowing = true

        allay.addPotionEffect(PotionEffect(PotionEffectType.LEVITATION, 5 * 20, 3, false, false))
        allay.world.getNearbyPlayers(allay.location, 30.0).forEach { player ->
            player.playSound {
                type(Sound.ENTITY_ZOMBIE_VILLAGER_CURE)
                volume(.5f)
            }
        }

        delay(5 * 1000)

        val location = allay.location.clone()
        allay.remove()

        location.world.spawnEntity(location, EntityType.VEX)
        location.world.spawnParticle(Particle.ANGRY_VILLAGER, location, 1)

        location.world.getNearbyPlayers(location, 30.0).forEach { player ->
            player.playSound {
                type(Sound.ENTITY_VEX_CHARGE)
                volume(.5f)
            }
        }
    }

}