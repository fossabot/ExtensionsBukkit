package ml.extbukkit.api.world;

import java.util.List;

/**
 * World manager
 */
public interface IWorldManager {
    /**
     * Get world by ID
     *
     * @param name World ID
     * @return World
     */
    IWorld getWorld(String name);

    /**
     * Get default world ID
     *
     * @return Default world ID
     */
    String getDefaultWorld();

    /**
     * Get world ID list
     *
     * @return World ID list
     */
    List<String> getWorlds();
}