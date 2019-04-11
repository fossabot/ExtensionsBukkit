package ml.extbukkit.api.world;

import ml.extbukkit.api.math.MathFunction;

/**
 * ExtensionLocation of something
 */
public interface Position {
    /**
     * Get position X coordinate
     *
     * @return X coordinate
     */
    double getX();

    /**
     * Get position Y coordinate
     *
     * @return Y coordinate
     */
    double getY();

    /**
     * Get position Z coordinate
     *
     * @return Z coordinate
     */
    double getZ();

    /**
     * Set position X coordinate
     *
     * @param x X coordinate
     */
    void setX(double x);

    /**
     * Set position Y coordinate
     *
     * @param y Y coordinate
     */
    void setY(double y);

    /**
     * Set position Z coordinate
     *
     * @param z Z coordinate
     */
    void setZ(double z);

    /**
     * Get position block X coordinate
     *
     * @return Block X coordinate
     */
    int getBlockX();

    /**
     * Get position block Y coordinate
     *
     * @return Block Y coordinate
     */
    int getBlockY();

    /**
     * Get position block Z coordinate
     *
     * @return Block Z coordinate
     */
    int getBlockZ();

    /**
     * Get vector of position
     */
    void getDirection();

    /**
     * Get yaw rotation
     *
     * @return Yaw rotation
     */
    float getYaw();

    /**
     * Get pitch rotation
     *
     * @return Pitch rotation
     */
    float getPitch();

    /**
     * Set yaw rotation
     *
     * @param yaw Yaw rotation
     */
    void setYaw(float yaw);

    /**
     * Set pitch rotation
     *
     * @param pitch Pitch rotation
     */
    void setPitch(float pitch);

    /**
     * Get position world
     *
     * @return ExtensionLocation world
     */
    String getWorld();

    /**
     * Copy position
     *
     * @return ExtensionLocation copy
     */
    Position copy();

    /**
     * Apply math function to position coordinates
     *
     * @param mathFunction Math function
     * @param x            X coordinate
     * @param y            Y coordinate
     * @param z            Z coordinate
     */
    void function(MathFunction mathFunction, double x, double y, double z);

    /**
     * Apply math function to each position coordinate
     *
     * @param mathFunction Math function
     * @param xyz          X, Y, Z coordinates
     */
    void function(MathFunction mathFunction, double xyz);
}