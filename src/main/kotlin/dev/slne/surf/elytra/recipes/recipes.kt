package dev.slne.surf.elytra.recipes

import dev.slne.surf.elytra.recipes.items.*
import dev.slne.surf.surfapi.bukkit.api.extensions.server
import dev.slne.surf.surfapi.bukkit.api.util.key
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe

val allayFoodRecipe = ShapedRecipe(key("allay_food"), allayFoodItemStack).apply {
    shape(
        "CDC",
        "DAD",
        "CDC"
    )

    setIngredient('C', ItemStack(Material.POPPED_CHORUS_FRUIT))
    setIngredient('D', ItemStack(Material.DIAMOND_BLOCK))
    setIngredient('A', ItemStack(Material.AMETHYST_BLOCK))
}

val ultimateWingCoreRecipe =
    ShapedRecipe(ultimateWingCoreKey, ultimateWingCoreItemStack).apply {
        shape(
            "PVP",
            "EGE",
            "FNF"
        )

        setIngredient('P', phantomVeilItemStack)
        setIngredient('V', vexEssenceItemStack)
        setIngredient('E', echoWingsItemStack)
        setIngredient('G', ghastTearCoreItemStack)
        setIngredient('F', featherOfDawnItemStack)
        setIngredient('N', nectarHeartItemStack)
    }

val ascendedWingCoreRecipe =
    ShapedRecipe(ascendedWingCoreKey, ascendedWingCoreItemStack).apply {
        shape(
            " S ",
            "SUS",
            " S "
        )

        setIngredient('S', ItemStack(Material.SHULKER_SHELL))
        setIngredient('U', ultimateWingCoreItemStack)
    }

val glacialBindingRecipe =
    ShapedRecipe(key("glacial_binding"), glacialBindingItemStack).apply {
        shape(
            "PIP",
            "ISI",
            "PIP"
        )

        setIngredient('P', ItemStack(Material.PACKED_ICE))
        setIngredient('S', snowmanHeartItemStack)
        setIngredient('I', ItemStack(Material.IRON_INGOT))
    }

val compressedGlacialBindingRecipe =
    ShapedRecipe(key("compressed_glacial_binding"), compressedGlacialBindingItemStack).apply {
        shape(
            "GGG",
            "GGG",
            "GGG"
        )

        setIngredient('G', glacialBindingItemStack)
    }

val doubleCompressedGlacialBindingRecipe =
    ShapedRecipe(
        key("double_compressed_glacial_binding"),
        doubleCompressedGlacialBindingItemStack
    ).apply {
        shape(
            "CCC",
            "CCC",
            "CCC"
        )

        setIngredient('C', compressedGlacialBindingItemStack)
    }

val soulThreadWeaveRecipe =
    ShapedRecipe(key("soul_thread_weave"), soulThreadWeaveItemStack).apply {
        shape(
            "SBS",
            "BAB",
            "SBS"
        )

        setIngredient('S', ItemStack(Material.STRING))
        setIngredient('B', ItemStack(Material.SOUL_SAND))
        setIngredient('A', ItemStack(Material.AMETHYST_SHARD))
    }

val compressedSoulThreadRecipe =
    ShapedRecipe(key("compressed_soul_thread_weave"), compressedSoulThreadWeaveItemStack).apply {
        shape(
            "SSS",
            "SSS",
            "SSS"
        )

        setIngredient('S', soulThreadWeaveItemStack)
    }

val doubleCompressedSoulThreadRecipe =
    ShapedRecipe(
        key("double_compressed_soul_thread_weave"),
        doubleCompressedSoulThreadWeaveItemStack
    ).apply {
        shape(
            "CCC",
            "CCC",
            "CCC"
        )

        setIngredient('C', compressedSoulThreadWeaveItemStack)
    }

val brokenPigeonWingsRecipe =
    ShapedRecipe(brokenPigeonWingsKey, brokenPigeonWingsItemStack).apply {
        shape(
            "VBV",
            "GWG",
            "PSP"
        )

        setIngredient('V', voidFeatherItemStack)
        setIngredient('B', boneFragmentItemStack)
        setIngredient('G', doubleCompressedGlacialBindingItemStack)
        setIngredient('W', ascendedWingCoreItemStack)
        setIngredient('P', blazingPowerItemStack)
        setIngredient('S', doubleCompressedSoulThreadWeaveItemStack)
    }

fun registerRecipes() {
    server.addRecipe(ultimateWingCoreRecipe)
    server.addRecipe(ascendedWingCoreRecipe)
    server.addRecipe(brokenPigeonWingsRecipe)

    server.addRecipe(glacialBindingRecipe)
    server.addRecipe(compressedGlacialBindingRecipe)
    server.addRecipe(doubleCompressedGlacialBindingRecipe)

    server.addRecipe(soulThreadWeaveRecipe)
    server.addRecipe(compressedSoulThreadRecipe)
    server.addRecipe(doubleCompressedSoulThreadRecipe)

    server.addRecipe(allayFoodRecipe)
}

fun discoverRecipes(player: Player) {
    player.discoverRecipe(key("ultimate_wing_core"))
    player.discoverRecipe(key("ascended_wing_core"))
    player.discoverRecipe(key("broken_celestial_glider"))

    player.discoverRecipe(key("glacial_binding"))
    player.discoverRecipe(key("compressed_glacial_binding"))
    player.discoverRecipe(key("double_compressed_glacial_binding"))

    player.discoverRecipe(key("soul_thread_weave"))
    player.discoverRecipe(key("compressed_soul_thread_weave"))
    player.discoverRecipe(key("double_compressed_soul_thread_weave"))

    player.discoverRecipe(key("allay_food"))
}