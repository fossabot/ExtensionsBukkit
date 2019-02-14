package ml.extbukkit.api.world.entity;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.world.IPositioned;
import ml.extbukkit.api.world.StraightDirection;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IEntity extends ICommandExecutor, IPositioned {
    boolean addPassenger(IEntity passenger);
    boolean addTag(String tag);
    boolean ejectPassengers();
    void getBoundingBox();
    int getId();
    StraightDirection getDirection();
    float getFallDistance();
    int getFireTicks();
    double getHeight();
    void getDamageEvent();
    int getMaxFireTicks();
    List<IEntity> getPassengers();
    int getPortalCooldown();
    Set<String> getTags();
    int getTicksExist();
    IEntityType getType();
    UUID getUUID();
    IEntity getPassengerOf();
    void getVelocity();
    double getWidth();
    boolean isMarkedForRemoval();
    boolean hasPassengers();
    boolean isPassenger();
    boolean isGlowing();
    boolean isExist();
    boolean ejectAsPassenger();
    void remove();
    boolean removePassenger(IEntity passenger);
    boolean removeTag(String tag);
    void setFallDistance(float distance);
    void setFireTicks(int ticks);
    void setGlowing(boolean glowing);
    void setDamageEvent(Object event);
    boolean setPassenger(IEntity passenger);
    void setPortalCooldown(int cooldown);
    void setSilent(boolean silent);
    void setTicksExist(int exist);
    void setVelocity(Object vector);
}