package dev.slne.surf.elytra.listeners

import com.github.shynixn.mccoroutine.folia.entityDispatcher
import com.github.shynixn.mccoroutine.folia.registerSuspendingEvents
import dev.slne.surf.elytra.plugin
import dev.slne.surf.elytra.recipes.items.MobItemDrop
import dev.slne.surf.surfapi.bukkit.api.extensions.pluginManager
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import kotlin.coroutines.CoroutineContext

object ListenerManager : Listener {

    fun registerListeners() {
        pluginManager.registerEvents(this, plugin)
        pluginManager.registerEvents(MobKillListener, plugin)
        pluginManager.registerEvents(HoneyListener, plugin)
        pluginManager.registerEvents(PigeonWingListener, plugin)
        pluginManager.registerEvents(WingCoreListener, plugin)
        pluginManager.registerEvents(SnowmanHeartListener, plugin)
        pluginManager.registerEvents(AllayListener, plugin)

        pluginManager.registerSuspendingEvents(VexListener, plugin, eventDispatcher)
    }

    private val eventDispatcher = mapOf<Class<out Event>, (event: Event) -> CoroutineContext>(
        Pair(PlayerInteractAtEntityEvent::class.java) {
            require(it is PlayerInteractAtEntityEvent)
            plugin.entityDispatcher(it.rightClicked)
        }
    )

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        MobItemDrop.entries.forEach {
            event.player.inventory.addItem(it.itemStack)
        }
    }


}