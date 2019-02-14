package ml.extbukkit.api.world;

import ml.extbukkit.main.world.Position;

public interface IPositioned {
    Position getPosition();
    boolean setPosition(Position position);
}