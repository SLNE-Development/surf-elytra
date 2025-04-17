package dev.slne.surf.elytra.altar

import com.github.shynixn.mccoroutine.folia.regionDispatcher
import dev.slne.surf.elytra.altar.listeners.BrewingStandClickListener
import dev.slne.surf.elytra.altar.listeners.BrewingStandDestroyListener
import dev.slne.surf.elytra.altar.listeners.ItemFrameClickListener
import dev.slne.surf.elytra.altar.listeners.ItemFrameDestroyListener
import dev.slne.surf.elytra.plugin
import dev.slne.surf.elytra.utils.AltarItemFrame
import dev.slne.surf.surfapi.bukkit.api.extensions.pluginManager
import kotlinx.coroutines.withContext
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.BrewingStand
import org.bukkit.entity.Player

object AltarManager {

    const val MAX_ALTAR_LEVEL = 100

    var altarAnimator: AltarAnimator? = null

    val altarWorld: World = Bukkit.getWorlds().first()
    private val altarNorthPosition = Location(altarWorld, 84.0, 64.0, 26.0)

    private val altarBrewingStandBlock get() = altarWorld.getBlockAt(altarNorthPosition)
    private val altarBrewingStand get() = altarBrewingStandBlock.state as BrewingStand

    fun initAltar() {
        initBrewingStand()
        AltarItemFrame.initAll()

        registerListeners()
    }

    suspend fun checkState(lastFillingPlayer: Player) {
        val state = AltarItemFrame.checkAllStates()

        if (!state) {
            return
        }

        val levelState = withContext(plugin.regionDispatcher(altarNorthPosition)) {
            val hasEnoughLevels = BrewingStandClickListener.getAltarLevels(
                lastFillingPlayer,
                altarBrewingStand
            ) >= MAX_ALTAR_LEVEL

            if (!hasEnoughLevels) {
                return@withContext false
            }

            BrewingStandClickListener.setAltarLevels(lastFillingPlayer, altarBrewingStand, 0)

            return@withContext true
        }

        if (!levelState) {
            return
        }

        altarAnimator = MainAltarAnimator(lastFillingPlayer)
        altarAnimator?.startAnimation()
    }

    private fun registerListeners() {
        pluginManager.registerEvents(ItemFrameDestroyListener, plugin)
        pluginManager.registerEvents(BrewingStandDestroyListener, plugin)
        pluginManager.registerEvents(ItemFrameClickListener, plugin)
        pluginManager.registerEvents(BrewingStandClickListener, plugin)
    }

    private fun initBrewingStand() {
        val altarNorthLocation = altarNorthPosition.toLocation(altarWorld)

        if (altarNorthLocation.block.type !== Material.BREWING_STAND) {
            altarNorthLocation.block.type = Material.BREWING_STAND
        }
    }

    fun isAltarBrewingStand(brewingStand: BrewingStand) = brewingStand == altarBrewingStand

    fun isAltarAnimationRunning() = altarAnimator != null

}