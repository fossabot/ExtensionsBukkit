package ml.extbukkit.api.world;

import java.util.List;

/**
 * SimpleWorld manager
 */
public interface WorldManager {
    /**
     * Get world by ID
     *
     * @param name SimpleWorld ID
     * @return SimpleWorld
     */
    World getWorld(String name);

    /**
     * Get default world ID
     *
     * @return Default world ID
     */
    String getDefaultWorld();

    /**
     * Get world ID list
     *
     * @return SimpleWorld ID list
     */
    List<String> getWorlds();
}