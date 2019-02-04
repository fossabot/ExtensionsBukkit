package ml.extbukkit.api.world;

import com.google.gson.JsonObject;

public interface IPerBlockHandler {
    boolean isTickable();
    default void tick() {}
    boolean hasFeature();
    <T> T getFeature(IFeature f);
    JsonObject writeData();
    void readData();
}