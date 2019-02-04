package ml.extbukkit.api.nbt;

import com.google.gson.JsonObject;

public interface INBT {
    JsonObject getNBT();
    void updateNBT(JsonObject json);
}