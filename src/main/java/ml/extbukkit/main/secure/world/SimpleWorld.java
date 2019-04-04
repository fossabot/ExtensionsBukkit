package ml.extbukkit.main.secure.world;

import ml.extbukkit.api.util.Wrapper;
import ml.extbukkit.api.world.Block;
import ml.extbukkit.api.world.Position;
import org.bukkit.Location;

public class SimpleWorld extends Wrapper<org.bukkit.World> implements ml.extbukkit.api.world.World {

    public SimpleWorld(org.bukkit.World w) {
        super(w);
    }

    @Override
    public Position position(double x, double y, double z) {
        return new ExtensionLocation(new Location(handle, x, y, z));
    }
    @Override
    public Position positionRotated(double x, double y, double z, float yaw, float pitch) {
        return new ExtensionLocation(new Location(handle, x, y, z, yaw, pitch));
    }
    @Override
    public Block getBlock(Position position) {
        return null;
    }
}