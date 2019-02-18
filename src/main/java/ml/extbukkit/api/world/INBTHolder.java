package ml.extbukkit.api.world;

import com.google.gson.JsonObject;

/**
 * Object, that can have NBT
 */
public interface INBTHolder {
    /**
     * Get object NBT
     *
     * @return Object NBT
     */
    JsonObject getNBT();

    /**
     * Update/Set object NBT
     *
     * @param nbt NBT to set
     */
    void updateNBT(JsonObject nbt);
}