package ml.extbukkit.api.world;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;

/**
 * Per block handler
 */
public interface PerBlockHandler {
    /**
     * Does it need to call method tick() every tick?
     *
     * @return Call tick() every tick?
     */
    boolean isTickable();

    /**
     * Tick function
     *
     * If isTickable() returns true, it will be run every tick
     */
    default void tick() {

    }

    /**
     * Check if handler has feature
     *
     * @param feature Feature
     * @return true, if handler has feature
     */
    boolean hasFeature(Feature feature);

    /**
     * Get instance of something
     *
     * @param feature Feature
     * @param <T> Type of returned instance
     * @return Instance of something
     */
    <T> T getFeature(Feature feature);

    /**
     * Write data to JSON
     * Needed to save block
     *
     * @return JSON data
     */
    JsonObject writeData();

    /**
     * Read data back from JSON
     * Needed to load block
     *
     * @param data JSON data
     */
    void readData(JSONObject data);
}