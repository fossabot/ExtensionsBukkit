package ml.extbukkit.api.world;

/**
 * SimpleWorld class
 */
public interface World {
    /**
     * Get position at coordinates
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return ExtensionLocation on coordinates
     */
    Position position(double x, double y, double z);

    /**
     * Get position at coordinates with rotation
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param yaw Yaw rotation of position
     * @param pitch Pitch rotation of position
     * @return ExtensionLocation on coordinates with rotation
     */
    Position positionRotated(double x, double y, double z, float yaw, float pitch);

    /**
     * Get block at position
     *
     * @param position ExtensionLocation where block is placed
     * @return Block on position
     */
    Block getBlock(Position position);
}