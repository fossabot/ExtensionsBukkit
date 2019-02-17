package ml.extbukkit.api.world.entity;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.world.INBTHolder;
import ml.extbukkit.api.world.IPositioned;
import ml.extbukkit.api.world.StraightDirection;

import java.util.List;
import java.util.UUID;

public interface IEntity extends ICommandExecutor, IPositioned, INBTHolder {
    boolean addPassenger(IEntity passenger);
    boolean ejectPassengers();
    void getBoundingBox();
    int getId();
    StraightDirection getDirection();
    double getHeight();
    void getDamageEvent();
    List<IEntity> getPassengers();
    int getTicksExist();
    IEntityType getType();
    UUID getUUID();
    IEntity getPassengerOf();
    double getWidth();
    boolean isMarkedForRemoval();
    boolean hasPassengers();
    boolean isPassenger();
    boolean isExist();
    boolean ejectAsPassenger();
    void remove();
    boolean removePassenger(IEntity passenger);
    void setDamageEvent(Object event);
    boolean setPassenger(IEntity passenger);
    void setTicksExist(int exist);
}