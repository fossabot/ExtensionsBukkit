package ml.extbukkit.main.world;

import ml.extbukkit.api.world.IWorld;
import ml.extbukkit.api.world.IBlock;
import ml.extbukkit.api.world.IPosition;
import org.bukkit.Location;

public class World implements IWorld {
    org.bukkit.World w;

    public World(org.bukkit.World w) {
        this.w = w;
    }

    @Override
    public IPosition position(double x, double y, double z) {
        return new Position(new Location(w, x, y, z));
    }
    @Override
    public IPosition positionRotated(double x, double y, double z, float yaw, float pitch) {
        return new Position(new Location(w, x, y, z, yaw, pitch));
    }
    @Override
    public IBlock getBlock(IPosition position) {
        return null;
    }
}