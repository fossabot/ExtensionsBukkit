package ml.extbukkit.api.world.entity;

import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.world.INBTHolder;
import ml.extbukkit.api.world.IPositioned;
import ml.extbukkit.api.world.StraightDirection;

import java.util.List;
import java.util.UUID;

public interface IEntity extends CommandExecutor, IPositioned, INBTHolder {
    boolean addPassenger(IEntity passenger);
    boolean ejectPassengers();
    void getBoundingBox();
    int getId();
    StraightDirection getDirection();
    int getFireTicks();
    double getHeight();
    void getDamageEvent();
    int getMaxFireTicks();
    List<IEntity> getPassengers();
    int getPortalCooldown();
    int getTicksExist();
    IEntityType getType();
    UUID getUUID();
    IEntity getPassengerOf();
    double getWidth();
    boolean isMarkedForRemoval();
    boolean hasPassengers();
    boolean isPassenger();
    boolean isGlowing();
    boolean isExist();
    boolean ejectAsPassenger();
    void remove();
    boolean removePassenger(IEntity passenger);
    void setFireTicks(int ticks);
    void setDamageEvent(Object event);
    boolean setPassenger(IEntity passenger);
    void setTicksExist(int exist);
}