package net.extbukkit.main.world;

import net.extbukkit.api.math.MathFunction;
import net.extbukkit.api.world.IPosition;
import net.extbukkit.main.server.Server;
import org.bukkit.Location;

public class Position implements IPosition {
    Location l;

    public Position(Location l) {
        this.l = l;
    }

    @Override
    public double getX() {
        return l.getX();
    }

    @Override
    public double getY() {
        return l.getY();
    }

    @Override
    public double getZ() {
        return l.getZ();
    }

    @Override
    public void setX(double x) {
        l.setX(x);
    }

    @Override
    public void setY(double y) {
        l.setY(y);
    }

    @Override
    public void setZ(double z) {
        l.setZ(z);
    }

    @Override
    public int getBlockX() {
        return l.getBlockX();
    }

    @Override
    public int getBlockY() {
        return l.getBlockY();
    }

    @Override
    public int getBlockZ() {
        return l.getBlockZ();
    }

    @Override
    public void getDirection() {

    }

    @Override
    public float getYaw() {
        return l.getYaw();
    }

    @Override
    public float getPitch() {
        return l.getPitch();
    }

    @Override
    public void setYaw(float yaw) {
        l.setYaw(yaw);
    }

    @Override
    public void setPitch(float pitch) {
        l.setPitch(pitch);
    }

    @Override
    public String getWorld() {
        return l.getWorld().getName();
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