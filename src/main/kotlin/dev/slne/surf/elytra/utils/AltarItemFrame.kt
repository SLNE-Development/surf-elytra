package dev.slne.surf.elytra.utils

import dev.slne.surf.elytra.altar.AltarManager.altarWorld
import dev.slne.surf.elytra.recipes.items.brokenCelestialGliderItemStack
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.SculkShrieker
import org.bukkit.entity.ItemFrame
import org.bukkit.inventory.ItemStack

enum class AltarItemFrame(
    private val location: Location,
    private val allowedItemStack: ItemStack
) {

    MIDDLE(
        Location(altarWorld, 84.0, 64.0, 30.0),
        brokenCelestialGliderItemStack
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    NORTH_EAST(
        Location(altarWorld, 87.0, 64.0, 27.0),
        ItemStack(Material.DRAGON_BREATH)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    EAST(
        Location(altarWorld, 88.0, 65.0, 30.0),
        ItemStack(Material.NETHER_STAR)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    SOUTH_EAST(
        Location(altarWorld, 87.0, 64.0, 33.0),
        ItemStack(Material.DRAGON_BREATH)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    SOUTH(
        Location(altarWorld, 84.0, 65.0, 34.0),
        ItemStack(Material.HEAVY_CORE)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    SOUTH_WEST(
        Location(altarWorld, 81.0, 64.0, 33.0),
        ItemStack(Material.DRAGON_BREATH)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    WEST(
        Location(altarWorld, 80.0, 65.0, 30.0),
        ItemStack(Material.NETHER_STAR)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    NORTH_WEST(
        Location(altarWorld, 81.0, 64.0, 27.0),
        ItemStack(Material.DRAGON_BREATH)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    };

    companion object {
        fun checkAllStates() = entries.all { it.checkState() }

        fun initAll() = entries.forEach { it.init() }

        fun getAltarItemFrameByItemFrame(itemFrame: ItemFrame) =
            entries.firstOrNull { it.itemFrame == itemFrame }

        fun isAltarItemFrame(itemFrame: ItemFrame) = getAltarItemFrameByItemFrame(itemFrame) != null
    }

    lateinit var itemFrame: ItemFrame
        private set

    fun init() {
        itemFrame = findOrSummon()
        itemFrame.isVisible = false
    }

    fun checkState() = !itemFrame.item.type.isAir

    fun isItemStackAllowed(itemStack: ItemStack) = itemStack.type == allowedItemStack.type

    abstract fun animate(currentFrame: Int): Boolean

    fun getShrieker() =
        itemFrame.location.clone().subtract(0.0, 1.0, 0.0).block.state as? SculkShrieker

    private fun findOrSummon() =
        location.getNearbyEntitiesByType(ItemFrame::class.java, 1.0).firstOrNull() ?: run {
            location.world.spawn(location, ItemFrame::class.java)
        }

}