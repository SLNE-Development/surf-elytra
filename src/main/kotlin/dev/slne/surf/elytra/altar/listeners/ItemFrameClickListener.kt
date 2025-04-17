package dev.slne.surf.elytra.altar.listeners

import com.github.shynixn.mccoroutine.folia.launch
import com.github.shynixn.mccoroutine.folia.ticks
import dev.slne.surf.elytra.altar.AltarManager
import dev.slne.surf.elytra.plugin
import dev.slne.surf.elytra.utils.AltarItemFrame
import dev.slne.surf.surfapi.core.api.messages.adventure.playSound
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
import io.papermc.paper.event.player.PlayerItemFrameChangeEvent
import kotlinx.coroutines.delay
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object ItemFrameClickListener : Listener {

    @EventHandler
    fun onPlayerItemFrameChange(event: PlayerItemFrameChangeEvent) {
        val itemFrame = event.itemFrame

        if (!AltarItemFrame.isAltarItemFrame(itemFrame)) {
            return
        }

        if (AltarManager.isAltarAnimationRunning()) {
            event.isCancelled = true

            event.player.sendText {
                appendPrefix()

                error("Aktuell läuft eine Animation. Solange diese läuft, kannst du keine weiteren Items hinzufügen")
            }

            return
        }

        val player = event.player

        val altarItemFrame = AltarItemFrame.getAltarItemFrameByItemFrame(itemFrame) ?: return
        val itemStack = event.itemStack

        if (!altarItemFrame.isItemStackAllowed(itemStack)) {
            event.isCancelled = true

            player.playSound {
                type(Sound.BLOCK_NOTE_BLOCK_BASS)
                volume(.75f)
            }

            player.sendText {
                appendPrefix()

                error("Dieses Item ist hier nicht erlaubt")
            }

            return
        }

        plugin.launch {
            delay(1.ticks)
            
            AltarManager.checkState(player)
        }
    }

}