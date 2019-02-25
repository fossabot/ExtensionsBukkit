package ml.extbukkit.main.secure.world.entity;

import ml.extbukkit.api.builtin.entities.EntityPig;
import ml.extbukkit.api.types.IEntityType;
import org.bukkit.entity.EntityType;

public class EntityHelper {
    public static EntityType ebToBukkit(IEntityType type) {
        if (type instanceof EntityPig)
            return EntityType.PIG;
        else return EntityType.UNKNOWN;
    }

    public static IEntityType bukkitToEB(EntityType type) {
        switch (type) {
            case DROPPED_ITEM:
                return null;
            case EXPERIENCE_ORB:
                return null;
            case AREA_EFFECT_CLOUD:
                return null;
            case ELDER_GUARDIAN:
                return null;
            case WITHER_SKELETON:
                return null;
            case STRAY:
                return null;
            case EGG:
                return null;
            case LEASH_HITCH:
                return null;
            case PAINTING:
                return null;
            case ARROW:
                return null;
            case SNOWBALL:
                return null;
            case FIREBALL:
                return null;
            case SMALL_FIREBALL:
                return null;
            case ENDER_PEARL:
                return null;
            case ENDER_SIGNAL:
                return null;
            case SPLASH_POTION:
                return null;
            case THROWN_EXP_BOTTLE:
                return null;
            case ITEM_FRAME:
                return null;
            case WITHER_SKULL:
                return null;
            case PRIMED_TNT:
                return null;
            case FALLING_BLOCK:
                return null;
            case FIREWORK:
                return null;
            case HUSK:
                return null;
            case SPECTRAL_ARROW:
                return null;
            case SHULKER_BULLET:
                return null;
            case DRAGON_FIREBALL:
                return null;
            case ZOMBIE_VILLAGER:
                return null;
            case SKELETON_HORSE:
                return null;
            case ZOMBIE_HORSE:
                return null;
            case ARMOR_STAND:
                return null;
            case DONKEY:
                return null;
            case MULE:
                return null;
            case EVOKER_FANGS:
                return null;
            case EVOKER:
                return null;
            case VEX:
                return null;
            case VINDICATOR:
                return null;
            case ILLUSIONER:
                return null;
            case MINECART_COMMAND:
                return null;
            case BOAT:
                return null;
            case MINECART:
                return null;
            case MINECART_CHEST:
                return null;
            case MINECART_FURNACE:
                return null;
            case MINECART_TNT:
                return null;
            case MINECART_HOPPER:
                return null;
            case MINECART_MOB_SPAWNER:
                return null;
            case CREEPER:
                return null;
            case SKELETON:
                return null;
            case SPIDER:
                return null;
            case GIANT:
                return null;
            case ZOMBIE:
                return null;
            case SLIME:
                return null;
            case GHAST:
                return null;
            case PIG_ZOMBIE:
                return null;
            case ENDERMAN:
                return null;
            case CAVE_SPIDER:
                return null;
            case SILVERFISH:
                return null;
            case BLAZE:
                return null;
            case MAGMA_CUBE:
                return null;
            case ENDER_DRAGON:
                return null;
            case WITHER:
                return null;
            case BAT:
                return null;
            case WITCH:
                return null;
            case ENDERMITE:
                return null;
            case GUARDIAN:
                return null;
            case SHULKER:
                return null;
            case PIG:
                return null;
            case SHEEP:
                return null;
            case COW:
                return null;
            case CHICKEN:
                return null;
            case SQUID:
                return null;
            case WOLF:
                return null;
            case MUSHROOM_COW:
                return null;
            case SNOWMAN:
                return null;
            case OCELOT:
                return null;
            case IRON_GOLEM:
                return null;
            case HORSE:
                return null;
            case RABBIT:
                return null;
            case POLAR_BEAR:
                return null;
            case LLAMA:
                return null;
            case LLAMA_SPIT:
                return null;
            case PARROT:
                return null;
            case VILLAGER:
                return null;
            case ENDER_CRYSTAL:
                return null;
            case TURTLE:
                return null;
            case PHANTOM:
                return null;
            case TRIDENT:
                return null;
            case COD:
                return null;
            case SALMON:
                return null;
            case PUFFERFISH:
                return null;
            case TROPICAL_FISH:
                return null;
            case DROWNED:
                return null;
            case DOLPHIN:
                return null;
            case LINGERING_POTION:
                return null;
            case FISHING_HOOK:
                return null;
            case LIGHTNING:
                return null;
            case WEATHER:
                return null;
            case PLAYER:
                return null;
            case COMPLEX_PART:
                return null;
            case TIPPED_ARROW:
                return null;
            case UNKNOWN:
                return null;
            default:
                return null;
        }
    }
}