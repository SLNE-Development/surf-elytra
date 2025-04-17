package dev.slne.surf.elytra.utils

import org.bukkit.World

data class BlockPosition(val x: Int, val y: Int, val z: Int) {
    fun toLocation(world: World) = world.getBlockAt(x, y, z).location
}
