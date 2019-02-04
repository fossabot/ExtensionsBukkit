package ml.extbukkit.api.world;

import ml.extbukkit.api.math.MathFunction;

public interface IPosition extends Cloneable {
    double getX();
    double getY();
    double getZ();
    void setX(double x);
    void setY(double y);
    void setZ(double z);
    int getBlockX();
    int getBlockY();
    int getBlockZ();
    void getDirection();
    float getYaw();
    float getPitch();
    void setYaw(float yaw);
    void setPitch(float pitch);
    String getWorld();
    IPosition copy();
    void function(MathFunction f, double x, double y, double z);
    void function(MathFunction f, double xyz);
}