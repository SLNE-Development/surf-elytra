package dev.slne.surf.elytra.recipes.items

import dev.slne.surf.surfapi.bukkit.api.builder.ItemStack
import dev.slne.surf.surfapi.bukkit.api.builder.buildLore
import dev.slne.surf.surfapi.bukkit.api.builder.displayName
import dev.slne.surf.surfapi.bukkit.api.builder.meta
import dev.slne.surf.surfapi.core.api.messages.adventure.key
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.persistence.PersistentDataType
import dev.slne.surf.surfapi.bukkit.api.util.key as namespacedKey

enum class ElytraItems(val itemStack: ItemStack) {
    ULTIMATE_WING_CORE(ultimateWingCoreItemStack),
    ASCENDED_WING_CORE(ascendedWingCoreItemStack),
    SOUL_THREAD_WEAVE(soulThreadWeaveItemStack),
    COMPRESSED_SOUL_THREAD_WEAVE(compressedSoulThreadWeaveItemStack),
    DOUBLE_COMPRESSED_SOUL_THREAD_WEAVE(doubleCompressedSoulThreadWeaveItemStack),
    GLACIAL_BINDING(glacialBindingItemStack),
    COMPRESSED_GLACIAL_BINDING(compressedGlacialBindingItemStack),
    DOUBLE_COMPRESSED_GLACIAL_BINDING(doubleCompressedGlacialBindingItemStack),
    BROKEN_PIGEON_WINGS(brokenPigeonWingsItemStack),
    PIGEON_WINGS(pigeonWingsItemStack);
}

val ultimateWingCoreKey = namespacedKey("ultimate_wing_core")
val ascendedWingCoreKey = namespacedKey("ascended_wing_core")
val brokenPigeonWingsKey = namespacedKey("broken_celestial_glider")
val snowmanHeartKey = namespacedKey("snowman_heart")

val ultimateWingCoreItemStack = ItemStack(Material.WIND_CHARGE) {
    displayName {
        primary("Ultimate Wing Core")
    }
    buildLore {
        line {
            spacer("Zusammengesetzt aus der Essenz seltener Kreaturen")
        }
        line { }
        line {
            spacer("Wer diese Essenz in sich trägt,")
        }
        line {
            spacer("kann die ultimative Kraft der Flügel entfesseln")
        }
    }
    editPersistentDataContainer { container ->
        container.set(ultimateWingCoreKey, PersistentDataType.BOOLEAN, true)
    }
}

val ascendedWingCoreItemStack = ItemStack(Material.WIND_CHARGE) {
    displayName {
        primary("Ascended Wing Core")
    }
    buildLore {
        line {
            spacer("Ein mit purer Dimensionskraft")
        }
        line {
            spacer("durchtränkter Kern")
        }
        line { }
        line {
            spacer("Nur jene, die das End bezwungen haben,")
        }
        line {
            spacer("können seinen Ruf hören")
        }
    }
    editPersistentDataContainer { container ->
        container.set(ascendedWingCoreKey, PersistentDataType.BOOLEAN, true)
    }
}

val soulThreadWeaveItemStack = ItemStack(Material.COBWEB) {
    displayName {
        primary("Soul Thread Weave")
    }
    buildLore {
        line {
            spacer("Ein Gewebe aus Seelenfäden,")
        }
        line {
            spacer("das die Dimensionen durchdringt")
        }
    }
}

val compressedSoulThreadWeaveItemStack = ItemStack(Material.COBWEB) {
    displayName {
        primary("Compressed Soul Thread Weave")
    }
    buildLore {
        line {
            spacer("Ein komprimierter Faden, der die Seelen")
        }
        line {
            spacer("der Dimensionen in sich trägt")
        }
    }
}

val doubleCompressedSoulThreadWeaveItemStack = ItemStack(Material.COBWEB) {
    displayName {
        primary("Double Compressed Soul Thread Weave")
    }
    buildLore {
        line {
            spacer("Ein doppelt komprimierter Faden, der die Seelen")
        }
        line {
            spacer("der Dimensionen in sich trägt")
        }
    }
}

val glacialBindingItemStack = ItemStack(Material.BLUE_ICE) {
    displayName {
        primary("Glacial Binding")
    }
    buildLore {
        line {
            spacer("Ein gefrorenes Band, das die Kälte")
        }
        line {
            spacer("der Dimensionen in sich trägt")
        }
    }
}

val compressedGlacialBindingItemStack = ItemStack(Material.BLUE_ICE) {
    displayName {
        primary("Compressed Glacial Binding")
    }
    buildLore {
        line {
            spacer("Ein komprimiertes Band, das die Kälte")
        }
        line {
            spacer("der Dimensionen in sich trägt")
        }
    }
}

val doubleCompressedGlacialBindingItemStack = ItemStack(Material.BLUE_ICE) {
    displayName {
        primary("Double Compressed Glacial Binding")
    }
    buildLore {
        line {
            spacer("Ein doppelt komprimiertes Band, das die Kälte")
        }
        line {
            spacer("der Dimensionen in sich trägt")
        }
    }
}

val brokenPigeonWingsItemStack = ItemStack(Material.ELYTRA) {
    displayName {
        primary("Broken Pigeon Wings")
    }
    buildLore {
        line {
            spacer("Die Flügel einer Taube")
        }
        line {
            spacer("Doch irgendwas stimmt nicht")
        }
        line { }
        line {
            spacer("Ich sollte herausfinden,")
        }
        line {
            spacer("was mit diesen Flügeln passiert ist")
        }
        line {
            spacer("und wie ich sie reparieren kann")
        }
    }

    meta<Damageable> {
        damage = Int.MAX_VALUE
    }

    addUnsafeEnchantment(Enchantment.DENSITY, 1)
    addItemFlags(ItemFlag.HIDE_ENCHANTS)

    editPersistentDataContainer { container ->
        container.set(brokenPigeonWingsKey, PersistentDataType.BOOLEAN, true)
    }
}

val pigeonWingsItemStack = ItemStack(Material.ELYTRA) {
    displayName {
        primary("Pigeon Wings")
    }
    buildLore {
        line {
            spacer("TODO")
        }
    }

    RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT)
        .get(key("minecraft", "soulbound"))
        ?.let { addUnsafeEnchantment(it, 1) }
}
