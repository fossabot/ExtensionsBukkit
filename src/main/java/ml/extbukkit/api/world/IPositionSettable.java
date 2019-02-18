package ml.extbukkit.api.world;

/**
 * Object, which position can be set
 */
public interface IPositionSettable {
    /**
     * Set position of object
     *
     * @param position Position to set
     * @return true, if position changed
     */
    boolean setPosition(IPosition position);
}