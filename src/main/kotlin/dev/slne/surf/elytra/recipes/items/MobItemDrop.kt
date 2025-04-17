package dev.slne.surf.elytra.recipes.items

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
    VEX_ESSENCE(vexEssenceItemStack, 0.5, EntityType.VEX),
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
    displayName {
        primary("Nectar Heart")
    }
    buildLore {
        line {
            spacer("Ein goldener Tropfen, süß und kraftvoll")
        }
        line {
            spacer("summend vor Energie")
        }
    }
}

val echoWingsItemStack = ItemStack(Material.ECHO_SHARD) {
    displayName {
        primary("Echo Wings")
    }
    buildLore {
        line {
            spacer("Ein Flügel aus Schatten,")
        }
        line {
            spacer("der in völliger Dunkelheit schimmert")
        }
    }
}

val featherOfDawnItemStack = ItemStack(Material.FEATHER) {
    displayName {
        primary("Feather of Dawn")
    }
    buildLore {
        line {
            spacer("Ein leuchtend weißes Federkleid")
        }
        line {
            spacer("durchzogen von magischer Wärme")
        }
    }
}

val ghastTearCoreItemStack = ItemStack(Material.GHAST_TEAR) {
    displayName {
        primary("Ghast Tear Core")
    }
    buildLore {
        line {
            spacer("Eine kondensierte, brennende")
        }
        line {
            spacer("Träne voller Verzweiflung")
        }
    }
}

val vexEssenceItemStack = ItemStack(Material.AMETHYST_SHARD) {
    displayName {
        primary("Vex Essence")
    }
    buildLore {
        line {
            spacer("Eine unruhige, funkelnde Kugel")
        }
        line {
            spacer("aus reiner Boshaftigkeit")
        }
    }
}

val phantomVeilItemStack = ItemStack(Material.PHANTOM_MEMBRANE) {
    displayName {
        primary("Phantom Veil")
    }
    buildLore {
        line {
            spacer("Ein geisterhafter Schleier")
        }
        line {
            spacer("flatternd im Wind, schwer zu fassen")
        }
    }
}

val blazingPowerItemStack = ItemStack(Material.BLAZE_ROD) {
    displayName {
        primary("Blazing Power")
    }
    buildLore {
        line {
            spacer("Ein glühendes, magisches Artefakt")
        }
        line {
            spacer("das die Kraft des Feuers in sich trägt")
        }
    }
}

val snowmanHeartItemStack = ItemStack(Material.SNOWBALL) {
    displayName {
        primary("Snowman Heart")
    }
    buildLore {
        line {
            spacer("Du bist ein Monster")
        }
    }
    editPersistentDataContainer { container ->
        container.set(snowmanHeartKey, PersistentDataType.BOOLEAN, true)
    }
}

val boneFragmentItemStack = ItemStack(Material.BONE) {
    displayName {
        primary("Bone Fragment")
    }
    buildLore {
        line {
            spacer("Ein Fragment längst")
        }
        line {
            spacer("vergessener Flugwesen")
        }
    }
}

val voidFeatherItemStack = ItemStack(Material.FEATHER) {
    displayName {
        primary("Void Feather")
    }

    buildLore {
        line {
            spacer("Ein Feder aus dem Nichts")
        }
        line {
            spacer("die in der Dunkelheit schimmert")
        }
    }

    addUnsafeEnchantment(Enchantment.DENSITY, 1)
    addItemFlags(ItemFlag.HIDE_ENCHANTS)
}