package dev.slne.surf.elytra.recipes.items

import dev.slne.surf.elytra.recipes.lore
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
    ALLAY_FOOD(allayFoodItemStack),
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

val allayFoodItemStack = ItemStack(Material.DIAMOND) {
    displayName { primary("Allay-Köder – Anlockungsmittel") }
    buildLore {
        lore(
            "Ein speziell entwickeltes Lockmittel für Allays.",
            "Die T.A.U.B.E nutzt Allays zur unauffälligen",
            "Sammlung von Gegenständen und Daten.",
            "",
            "Enthält feinste Diamantpartikel, die Allays",
            "magnetisch anziehen und dauerhaft binden.",
            "",
            "Warnung:",
            "Anhaltender Einsatz könnte Allays aggressiv machen",
            "oder unkontrollierte Sammelwut auslösen.",
            "",
            "Nur geschultes Personal darf dieses Mittel einsetzen.",
            "",
            "\"Die Kunst der Täuschung ist unser bester Verbündeter.\"",
            "– T.A.U.B.E-Handbuch, Abschnitt 3.2"
        ).forEach { l -> line { spacer(l) } }
    }
}

val ultimateWingCoreItemStack = ItemStack(Material.WIND_CHARGE) {
    displayName { primary("Ultimativer Flügelschaltkern – Kernsystem") }
    buildLore {
        lore(
            "Das Herzstück der T.A.U.B.E., verantwortlich für alle",
            "internen Operationen und Steuerungen.",
            "Jede Bewegung, jede Entscheidung beginnt hier."
        ).forEach { l -> line { spacer(l) } }
    }
    editPersistentDataContainer { it.set(ultimateWingCoreKey, PersistentDataType.BOOLEAN, true) }
}

val ascendedWingCoreItemStack = ItemStack(Material.WIND_CHARGE) {
    displayName { primary("Erhabener Flügelschaltkern – Erweiterte KI") }
    buildLore {
        lore(
            "Eine verbesserte Version des ultimativen Kerns mit künstlicher Intelligenz.",
            "Lässt die T.A.U.B.E autonom Entscheidungen treffen",
            "und komplexe Operationen eigenständig durchführen."
        ).forEach { l -> line { spacer(l) } }
    }
    editPersistentDataContainer { it.set(ascendedWingCoreKey, PersistentDataType.BOOLEAN, true) }
}

val soulThreadWeaveItemStack = ItemStack(Material.COBWEB) {
    displayName { primary("Seelenfaden-Geflecht – Kommunikation") }
    buildLore {
        lore(
            "Ein fein gewebtes Netz aus Seelenfäden, das als",
            "Kommunikationsmedium dient und eine schnelle",
            "und sichere Datenübertragung gewährleistet."
        ).forEach { l -> line { spacer(l) } }
    }
}

val compressedSoulThreadWeaveItemStack = ItemStack(Material.COBWEB) {
    displayName { primary("Komprimiertes Seelenfaden-Geflecht – Hochleistungs-Kommunikation") }
    buildLore {
        lore(
            "Ein verbessertes Seelenfaden-Geflecht, das höhere Datenvolumen",
            "und stabilere Verbindungen selbst über große Entfernungen ermöglicht.",
            "Optimal für umfangreiche Überwachungseinsätze."
        ).forEach { l -> line { spacer(l) } }
    }
}

val doubleCompressedSoulThreadWeaveItemStack = ItemStack(Material.COBWEB) {
    displayName { primary("Doppelt Komprimiertes Seelenfaden-Geflecht – Absolute Kommunikationssicherheit") }
    buildLore {
        lore(
            "Die fortschrittlichste Form der Kommunikationsnetze.",
            "Sicher gegen jede Form von Abhörung und ideal für",
            "streng geheime Operationen geeignet."
        ).forEach { l -> line { spacer(l) } }
    }
}

val glacialBindingItemStack = ItemStack(Material.BLUE_ICE) {
    displayName { primary("Gletscherbindung – Grundkühlung") }
    buildLore {
        lore(
            "Ein Kühlsystem, das die interne Elektronik der T.A.U.B.E vor Überhitzung schützt.",
            "Ermöglicht einen stabilen Betrieb auch unter anspruchsvollen Bedingungen."
        ).forEach { l -> line { spacer(l) } }
    }
}

val compressedGlacialBindingItemStack = ItemStack(Material.BLUE_ICE) {
    displayName { primary("Komprimierte Gletscherbindung – Erweiterte Kühlung") }
    buildLore {
        lore(
            "Ein verbessertes Kühlsystem, das selbst bei extremen Einsätzen",
            "für optimale Betriebstemperaturen sorgt."
        ).forEach { l -> line { spacer(l) } }
    }
}

val doubleCompressedGlacialBindingItemStack = ItemStack(Material.BLUE_ICE) {
    displayName { primary("Doppelt Komprimierte Gletscherbindung – Hochleistungs-Kühlsystem") }
    buildLore {
        lore(
            "Ein Kühlsystem der Spitzenklasse, das selbst größte Wärmeentwicklung",
            "bei Hochleistungseinsätzen vollständig neutralisiert."
        ).forEach { l -> line { spacer(l) } }
    }
}

val brokenPigeonWingsItemStack = ItemStack(Material.ELYTRA) {
    displayName { primary("Defekte Taubenflügel – Rekonstruktionsbasis") }
    buildLore {
        lore(
            "Beschädigte Flügel einer ausgefallenen T.A.U.B.E.",
            "Dienen als Basis für die Rekonstruktion und Reparatur",
            "des endgültigen Fluggeräts."
        ).forEach { l -> line { spacer(l) } }
    }
    meta<Damageable> { damage = Int.MAX_VALUE }
    addUnsafeEnchantment(Enchantment.DENSITY, 1)
    addItemFlags(ItemFlag.HIDE_ENCHANTS)
    editPersistentDataContainer { it.set(brokenPigeonWingsKey, PersistentDataType.BOOLEAN, true) }
}

val pigeonWingsItemStack = ItemStack(Material.ELYTRA) {
    displayName { primary("Taubenflügel") }
    buildLore {
        lore(
            "Die finalen, einsatzbereiten Flügel der T.A.U.B.E",
            "Geräuschlos, zuverlässig und für jede Mission geeignet.",
            "Mit diesen Flügeln ist die T.A.U.B.E vollständig operativ und bereit",
            "für ihren Einsatz am Himmel über den Städten."
        ).forEach { l -> line { spacer(l) } }
    }
    RegistryAccess.registryAccess().getRegistry(RegistryKey.ENCHANTMENT)
        .get(key("minecraft", "soulbound"))
        ?.let { addUnsafeEnchantment(it, 1) }
}