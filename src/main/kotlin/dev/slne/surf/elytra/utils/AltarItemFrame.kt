package dev.slne.surf.elytra.utils

import dev.slne.surf.elytra.altar.AltarManager.altarWorld
import dev.slne.surf.elytra.recipes.items.brokenPigeonWingsItemStack
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
        Location(altarWorld, -23.0, 105.0, 15.0),
        brokenPigeonWingsItemStack
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    NORTH(
        Location(altarWorld, -23.0, 106.0, 11.0),
        ItemStack(Material.HEAVY_CORE)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    NORTH_EAST(
        Location(altarWorld, -20.0, 105.0, 12.0),
        ItemStack(Material.DRAGON_BREATH)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    EAST(
        Location(altarWorld, -19.0, 106.0, 15.0),
        ItemStack(Material.NETHER_STAR)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    SOUTH_EAST(
        Location(altarWorld, -20.0, 105.0, 18.0),
        ItemStack(Material.DRAGON_BREATH)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    SOUTH_WEST(
        Location(altarWorld, -26.0, 105.0, 18.0),
        ItemStack(Material.DRAGON_BREATH)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    WEST(
        Location(altarWorld, -27.0, 106.0, 15.0),
        ItemStack(Material.NETHER_STAR)
    ) {
        override fun animate(currentFrame: Int): Boolean {
            itemFrame.setItem(null)
            return true
        }
    },

    NORTH_WEST(
        Location(altarWorld, -26.0, 105.0, 12.0),
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