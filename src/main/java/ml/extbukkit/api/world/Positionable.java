package ml.extbukkit.api.world;

public interface Positionable {
    /**
     * Get position of object
     *
     * @return ExtensionLocation of object
     */
    Position getPosition();

    /**
     * Set position of object
     *
     * @param position ExtensionLocation to set
     * @return true, if position changed
     */
    boolean setPosition(Position position);
}
