package ml.extbukkit.api.world.entity;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.world.INBTHolder;
import ml.extbukkit.api.world.IPositioned;
import ml.extbukkit.api.world.StraightDirection;

import java.util.List;
import java.util.UUID;

/**
 * Entity class
 */
public interface IEntity extends ICommandExecutor, IPositioned, INBTHolder {
    /**
     * Add passenger to entity
     *
     * @param passenger Entity to add as passenger
     * @return true, if passenger added successfully
     */
    boolean addPassenger(IEntity passenger);

    /**
     * Eject all passengers of entity
     *
     * @return true, if passengers ejected successfully
     */
    boolean ejectPassengers();

    /**
     * Get entity bounding box
     */
    void getBoundingBox();

    /**
     * Get entity ID
     *
     * @return Entity ID
     */
    int getId();

    /**
     * Get entity straight direction
     *
     * @return Entity straight direction
     */
    StraightDirection getStraightDirection();

    /**
     * Get entity direction
     */
    void getDirection();

    /**
     * Get entity height
     *
     * @return Entity height
     */
    double getHeight();

    /**
     * Get last damage event
     */
    void getDamageEvent();

    /**
     * Get entity passengers
     *
     * @return Entity passengers
     */
    List<IEntity> getPassengers();

    /**
     * Get ticks entity exist
     *
     * @return Ticks exist
     */
    int getTicksExist();

    /**
     * Get entity type
     *
     * @return Entity type
     */
    IEntityType getType();

    /**
     * Get entity UUID
     *
     * @return Entity UUID
     */
    UUID getUUID();

    /**
     * Get a entity, that entity riding
     *
     * @return Passenger of
     */
    IEntity getPassengerOf();

    /**
     * Get entity width
     *
     * @return Width of entity
     */
    double getWidth();

    /**
     * Is entity marked for removal
     *
     * @return true, if entity is marked for removal
     */
    boolean isMarkedForRemoval();

    /**
     * Has entity passengers
     *
     * @return true, if entity has passengers
     */
    boolean hasPassengers();

    /**
     * Is entity a passenger
     *
     * @return true, if entity is a passenger
     */
    boolean isPassenger();

    /**
     * Is entity exists
     *
     * @return true, if entity exists
     */
    boolean isExist();

    /**
     * Eject from an entity, that entity is riding
     *
     * @return true, if entity ejected successfully
     */
    boolean ejectAsPassenger();

    /**
     * Remove entity from the world
     */
    void remove();

    /**
     * Remove a passenger from entity
     *
     * @param passenger Passenger to remove
     * @return true, if passenger removed successfully
     */
    boolean removePassenger(IEntity passenger);

    /**
     * Set last damage event
     *
     * @param event Last damage event
     */
    void setDamageEvent(Object event);

    /**
     * Remove all passengers and add a passenger
     *
     * @param passenger Passenger to set
     * @return true, if passenger set successfully
     */
    boolean setPassenger(IEntity passenger);

    /**
     * Set ticks entity exist
     *
     * @param exist Ticks entity exist
     */
    void setTicksExist(int exist);
}