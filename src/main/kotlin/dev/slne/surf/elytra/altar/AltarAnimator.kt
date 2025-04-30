package dev.slne.surf.elytra.altar

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.launch
import com.github.shynixn.mccoroutine.folia.ticks
import dev.slne.surf.elytra.plugin
import dev.slne.surf.elytra.utils.AltarItemFrame
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.bukkit.entity.Player

abstract class AltarAnimator(protected val lastFillingPlayer: Player) {

    private var isAnimating = true
    private var currentFrame = 0

    abstract suspend fun onCompletion()

    fun startAnimation() {
        plugin.launch {
            AltarItemFrame.entries.forEach { itemFrame ->
                launch(plugin.entityDispatcher(itemFrame.itemFrame)) {
                    itemFrame.getShrieker()?.tryShriek(lastFillingPlayer)
                }
            }

            while (isAnimating) {
                animate()
            }
        }
    }

    fun stopAnimation() {
        isAnimating = false
    }

    private suspend fun animate() {
        coroutineScope {
            val allAnimationsDone = AltarItemFrame.entries.map { itemFrame ->
                async(plugin.entityDispatcher(itemFrame.itemFrame)) {
                    itemFrame.animate(currentFrame)
                }
            }.all { it.await() }

            if (allAnimationsDone) {
                isAnimating = false
                onCompletion()
            }
        }

        currentFrame++

        delay(1.ticks)
    }

}