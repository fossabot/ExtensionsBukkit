package ml.extbukkit.api.world;

public interface IPositioned {
    IPosition getPosition();
    boolean setPosition(IPosition position);
}