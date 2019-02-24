package ml.extbukkit.api.world;

public interface IPositionable
{
    /**
     * Get position of object
     *
     * @return Position of object
     */
    IPosition getPosition();

    /**
     * Set position of object
     *
     * @param position Position to set
     * @return true, if position changed
     */
    boolean setPosition(IPosition position);
}
