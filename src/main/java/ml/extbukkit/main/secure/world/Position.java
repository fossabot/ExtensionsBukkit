package ml.extbukkit.main.secure.world;

import ml.extbukkit.api.math.MathFunction;
import ml.extbukkit.api.util.AWrapper;
import ml.extbukkit.api.world.IPosition;
import ml.extbukkit.main.secure.server.Server;
import org.bukkit.Location;

public class Position extends AWrapper<Location> implements IPosition {
    public Position(Location l) {
        super(l);
    }

    @Override
    public double getX() {
        return handle.getX();
    }

    @Override
    public double getY() {
        return handle.getY();
    }

    @Override
    public double getZ() {
        return handle.getZ();
    }

    @Override
    public void setX(double x) {
        handle.setX(x);
    }

    @Override
    public void setY(double y) {
        handle.setY(y);
    }

    @Override
    public void setZ(double z) {
        handle.setZ(z);
    }

    @Override
    public int getBlockX() {
        return handle.getBlockX();
    }

    @Override
    public int getBlockY() {
        return handle.getBlockY();
    }

    @Override
    public int getBlockZ() {
        return handle.getBlockZ();
    }

    @Override
    public void getDirection() {
        
    }

    @Override
    public float getYaw() {
        return handle.getYaw();
    }

    @Override
    public float getPitch() {
        return handle.getPitch();
    }

    @Override
    public void setYaw(float yaw) {
        handle.setYaw(yaw);
    }

    @Override
    public void setPitch(float pitch) {
        handle.setPitch(pitch);
    }

    @Override
    public String getWorld() {
        return handle.getWorld().getName();
    }

    @Override
    public IPosition copy() {
        return Server.getInstance().getWorldManager().getWorld(getWorld()).positionRotated(getX(), getY(), getZ(), getYaw(), getPitch());
    }

    @Override
    public void function(MathFunction f, double x, double y, double z) {
        switch(f) {
            case ADD:
                setX(getX() + x);
                setY(getY() + y);
                setZ(getZ() + z);
                break;
            case SUBTRACT:
                setX(getX() - x);
                setY(getY() - y);
                setZ(getZ() - z);
                break;
            case MULTIPLY:
                setX(getX() * x);
                setY(getY() * y);
                setZ(getZ() * z);
                break;
            case DIVIDE:
                setX(getX() / x);
                setY(getY() / y);
                setZ(getZ() / z);
                break;
            default:
                break;
        }
    }

    @Override
    public void function(MathFunction f, double xyz) {
        function(f, xyz, xyz, xyz);
    }
}