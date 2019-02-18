package ml.extbukkit.api.world;

/**
 * World class
 */
public interface IWorld {
    /**
     * Get position at coordinates
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return Position on coordinates
     */
    IPosition position(double x, double y, double z);

    /**
     * Get position at coordinates with rotation
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @param yaw Yaw rotation of position
     * @param pitch Pitch rotation of position
     * @return Position on coordinates with rotation
     */
    IPosition positionRotated(double x, double y, double z, float yaw, float pitch);

    /**
     * Get block at position
     *
     * @param position Position where block is placed
     * @return Block on position
     */
    IBlock getBlock(IPosition position);
}