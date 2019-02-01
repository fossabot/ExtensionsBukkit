package net.extbukkit.api.world;

public interface IWorld {
    IPosition position(double x, double y, double z);
    IPosition positionRotated(double x, double y, double z, float yaw, float pitch);
    IBlock getBlock(IPosition position);
}