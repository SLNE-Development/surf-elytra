package dev.slne.surf.elytra.altar

import com.github.shynixn.mccoroutine.folia.regionDispatcher
import dev.slne.surf.elytra.plugin
import dev.slne.surf.elytra.recipes.items.celestialGliderItemStack
import dev.slne.surf.elytra.utils.AltarItemFrame
import dev.slne.surf.surfapi.core.api.messages.adventure.playSound
import kotlinx.coroutines.withContext
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player

class MainAltarAnimator(lastFillingPlayer: Player) : AltarAnimator(lastFillingPlayer) {

    override suspend fun onCompletion() {
        AltarManager.altarAnimator = null

        val middleLocation = AltarItemFrame.MIDDLE.itemFrame.location

        withContext(plugin.regionDispatcher(middleLocation)) {
            middleLocation.world.dropItem(
                middleLocation.clone().add(0.0, 1.0, 0.0),
                celestialGliderItemStack
            ) { item ->
                item.owner = lastFillingPlayer.uniqueId
                item.setCanMobPickup(false)
                item.isUnlimitedLifetime = false
                item.setWillAge(false)
            }

            middleLocation.world.spawnParticle(Particle.EXPLOSION, middleLocation, 1)
            middleLocation.getNearbyPlayers(30.0).forEach { player ->
                player.playSound {
                    type(Sound.ENTITY_GENERIC_EXPLODE)
                    volume(.75f)
                }
            }
        }
    }
}