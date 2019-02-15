package ml.extbukkit.api.world;

import com.google.gson.JsonObject;

public interface INBTHolder {
    JsonObject getNBT();
    void updateNBT(JsonObject nbt);
}