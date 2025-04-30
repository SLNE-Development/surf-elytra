package dev.slne.surf.elytra.recipes

fun lore(vararg lines: String) = buildList {
    lines.forEach { add(it) }
}