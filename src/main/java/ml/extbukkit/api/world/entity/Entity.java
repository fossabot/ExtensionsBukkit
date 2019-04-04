package ml.extbukkit.api.world.entity;

import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.types.EntityType;
import ml.extbukkit.api.world.NBTHolder;
import ml.extbukkit.api.world.Positionable;
import ml.extbukkit.api.world.StraightDirection;

import java.util.List;
import java.util.UUID;

/**
 * ExtensionedEntity class
 */
public interface Entity extends CommandExecutor, Positionable, NBTHolder {
    /**
     * Add passenger to entity
     *
     * @param passenger ExtensionedEntity to add as passenger
     * @return true, if passenger added successfully
     */
    boolean addPassenger(Entity passenger);

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
     * @return ExtensionedEntity ID
     */
    int getId();

    /**
     * Get entity straight direction
     *
     * @return ExtensionedEntity straight direction
     */
    StraightDirection getDirection();

    /**
     * Get entity height
     *
     * @return ExtensionedEntity height
     */
    double getHeight();

    /**
     * Get last damage event
     */
    void getDamageEvent();

    /**
     * Get entity passengers
     *
     * @return ExtensionedEntity passengers
     */
    List<Entity> getPassengers();

    /**
     * Get ticks entity exist
     *
     * @return Ticks exist
     */
    int getTicksExist();

    /**
     * Get entity type
     *
     * @return ExtensionedEntity type
     */
    EntityType getType();

    /**
     * Get entity UUID
     *
     * @return ExtensionedEntity UUID
     */
    UUID getUUID();

    /**
     * Get a entity, that entity riding
     *
     * @return Passenger of
     */
    Entity getPassengerOf();

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
    boolean removePassenger(Entity passenger);

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
    boolean setPassenger(Entity passenger);

    /**
     * Set ticks entity exist
     *
     * @param exist Ticks entity exist
     */
    void setTicksExist(int exist);
}