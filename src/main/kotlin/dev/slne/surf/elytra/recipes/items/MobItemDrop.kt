package dev.slne.surf.elytra.recipes.items

import dev.slne.surf.elytra.recipes.lore
import dev.slne.surf.surfapi.bukkit.api.builder.ItemStack
import dev.slne.surf.surfapi.bukkit.api.builder.buildLore
import dev.slne.surf.surfapi.bukkit.api.builder.displayName
import dev.slne.surf.surfapi.core.api.util.toObjectSet
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

enum class MobItemDrop(
    val itemStack: ItemStack,
    val probability: Double,
    vararg val entityType: EntityType
) {
    NECTAR_HEART(nectarHeartItemStack, 1.0),
    ECHO_WINGS(echoWingsItemStack, 5.0, EntityType.BAT),
    FEATHER_OF_DAWN(featherOfDawnItemStack, 0.25, EntityType.CHICKEN),
    GHAST_TEAR_CORE(ghastTearCoreItemStack, 0.5, EntityType.GHAST),
    VEX_ESSENCE(vexEssenceItemStack, 5.0, EntityType.VEX),
    PHANTOM_VEIL(phantomVeilItemStack, 0.5, EntityType.PHANTOM),
    BLAZING_POWER(blazingPowerItemStack, 0.5, EntityType.BLAZE),
    SNOWMAN_HEART(snowmanHeartItemStack, 25.0, EntityType.SNOW_GOLEM),
    BONE_FRAGMENT(boneFragmentItemStack, 50.0, EntityType.SKELETON_HORSE),
    VOID_FEATHER(voidFeatherItemStack, 0.5, EntityType.ENDERMAN);

    companion object {
        fun getItemDropsByEntityType(entityType: EntityType) = entries.filter {
            it.entityType.toList().contains(entityType)
        }.toObjectSet()
    }
}

val nectarHeartItemStack = ItemStack(Material.HONEYCOMB) {
    displayName { primary("Logbuch: Nektarherz - Energiequelle") }
    buildLore {
        lore(
            "Ein mit Bienenenergie aufgeladenes Herz,",
            "das kontinuierlich Strom für das T.A.U.B.E.-Projekt liefert.",
            "Ohne dieses Herz bleibt jede T.A.U.B.E am Boden."
        ).forEach { l -> line { spacer(l) } }
    }
}

val echoWingsItemStack = ItemStack(Material.ECHO_SHARD) {
    displayName { primary("Logbuch: Echoflügel – Sonarsystem") }
    buildLore {
        lore(
            "Diese Echoflügel ermöglichen der T.A.U.B.E eine präzise Navigation.",
            "Sie senden und empfangen Schallwellen, um auch im Dunkeln",
            "und bei schlechtem Wetter exakt zu operieren."
        ).forEach { l -> line { spacer(l) } }
    }
}

val featherOfDawnItemStack = ItemStack(Material.FEATHER) {
    displayName { primary("Logbuch: Feder der Morgendämmerung - Tarnung") }
    buildLore {
        lore(
            "Diese Feder verleiht der T.A.U.B.E Tarnfähigkeiten bei Tagesanbruch.",
            "Die Sensoren kalibrieren sich automatisch, um unsichtbar zu bleiben.",
            "So wird sie Teil des Morgenhimmels, unauffällig und effektiv."
        ).forEach { l -> line { spacer(l) } }
    }
}

val ghastTearCoreItemStack = ItemStack(Material.GHAST_TEAR) {
    displayName { primary("Logbuch: Ghasttränen-Kern Antrieb") }
    buildLore {
        lore(
            "Ein hocheffizienter Kern, der aus Ghasttränen Energie gewinnt.",
            "Er sorgt für genügend Schubkraft, um extreme Höhen zu erreichen.",
            "Jede Träne steigert die Flugleistung exponentiell."
        ).forEach { l -> line { spacer(l) } }
    }
}

val vexEssenceItemStack = ItemStack(Material.AMETHYST_SHARD) {
    displayName { primary("Logbuch: Vex-Essenz - Ablenkung") }
    buildLore {
        lore(
            "Diese Essenz ermöglicht eine unvorhersehbare Flugbahn.",
            "Radar- und Ortungssysteme werden durch ihre chaotische",
            "Natur verwirrt, was die T.A.U.B.E schwer verfolgbar macht."
        ).forEach { l -> line { spacer(l) } }
    }
}

val phantomVeilItemStack = ItemStack(Material.PHANTOM_MEMBRANE) {
    displayName { primary("Logbuch: Phantomschleier – Optische Tarnung") }
    buildLore {
        lore(
            "Ein Schleier, der die T.A.U.B.E vor visueller Erkennung schützt.",
            "Er manipuliert Licht und Schatten, sodass Beobachter",
            "lediglich harmlose optische Täuschungen wahrnehmen."
        ).forEach { l -> line { spacer(l) } }
    }
}

val blazingPowerItemStack = ItemStack(Material.BLAZE_ROD) {
    displayName { primary("Logbuch: Lodernde Macht - Notfallboost") }
    buildLore {
        lore(
            "Ein spezieller Energiespeicher für Notfälle.",
            "Dieser Blaze-Stab kann kurzfristig enorme Geschwindigkeit erzeugen,",
            "ideal, um in kritischen Situationen schnell zu entkommen."
        ).forEach { l -> line { spacer(l) } }
    }
}

val snowmanHeartItemStack = ItemStack(Material.SNOWBALL) {
    displayName { primary("Logbuch: Schneemann-Herz - Kühlsystem") }
    buildLore {
        lore(
            "Das Schneemann-Herz reguliert die Temperatur der T.A.U.B.E.",
            "Es verhindert Überhitzung und hält alle Systeme stabil.",
            "Unverzichtbar für den Betrieb bei hohen Belastungen."
        ).forEach { l -> line { spacer(l) } }
    }
    editPersistentDataContainer { it.set(snowmanHeartKey, PersistentDataType.BOOLEAN, true) }
}

val boneFragmentItemStack = ItemStack(Material.BONE) {
    displayName { primary("Logbuch: Knochenfragment - Struktur") }
    buildLore {
        lore(
            "Stabile Knochenfragmente bilden das tragfähige Gerüst.",
            "Sie bieten der T.A.U.B.E eine robuste Struktur,",
            "damit sie selbst bei stärksten Belastungen fliegen kann."
        ).forEach { l -> line { spacer(l) } }
    }
}

val voidFeatherItemStack = ItemStack(Material.FEATHER) {
    displayName { primary("Logbuch: Leerenfeder – Geräuschloser Flug") }
    buildLore {
        lore(
            "Diese Feder sorgt für absolute Stille im Flug.",
            "Die T.A.U.B.E wird damit geräuschlos und somit praktisch",
            "unmöglich zu entdecken oder zu verfolgen."
        ).forEach { l -> line { spacer(l) } }
    }
    addUnsafeEnchantment(Enchantment.DENSITY, 1)
    addItemFlags(ItemFlag.HIDE_ENCHANTS)
}