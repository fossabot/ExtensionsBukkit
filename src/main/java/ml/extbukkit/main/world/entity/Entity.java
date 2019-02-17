package ml.extbukkit.main.world.entity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.google.gson.JsonObject;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.util.Wrapper;
import ml.extbukkit.api.world.IPosition;
import ml.extbukkit.api.world.StraightDirection;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.nms.NBTUtils;
import ml.extbukkit.main.server.Server;
import ml.extbukkit.main.world.DirectionHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Entity extends Wrapper<Player> implements IEntity {

    public Entity(Player handle) {
        super(handle);
    }

    @Override
    public boolean addPassenger(IEntity passenger) {
        // TODO
        return false;
    }

    @Override
    public boolean ejectPassengers() {
        return handle.eject();
    }

    @Override
    public void getBoundingBox() {

    }

    @Override
    public int getId() {
        return handle.getEntityId();
    }

    @Override
    public StraightDirection getDirection() {
        return DirectionHelper.bukkitToStraight(handle.getFacing());
    }

    @Override
    public double getHeight() {
        return handle.getHeight();
    }

    @Override
    public void getDamageEvent() {
        // TODO
    }

    @Override
    public List<IEntity> getPassengers() {
        // TODO
        return null;
    }

    @Override
    public int getTicksExist() {
        return handle.getTicksLived();
    }

    @Override
    public IEntityType getType() {
        // TODO
        return null;
    }

    @Override
    public UUID getUUID() {
        return handle.getUniqueId();
    }

    @Override
    public IEntity getPassengerOf() {
        // TODO
        return null;
    }

    @Override
    public double getWidth() {
        return handle.getWidth();
    }

    @Override
    public boolean isMarkedForRemoval() {
        return handle.isDead();
    }

    @Override
    public boolean hasPassengers() {
        return !handle.getPassengers().isEmpty();
    }

    @Override
    public boolean isPassenger() {
        return handle.isInsideVehicle();
    }

    @Override
    public boolean isExist() {
        return handle.isValid();
    }

    @Override
    public boolean ejectAsPassenger() {
        return handle.leaveVehicle();
    }

    @Override
    public void remove() {
        handle.remove();
    }

    @Override
    public boolean removePassenger(IEntity passenger) {
        // TODO
        return false;
    }

    @Override
    public void setDamageEvent(Object event) {
        // TODO
    }

    @Override
    public boolean setPassenger(IEntity passenger) {
        return ejectPassengers() || addPassenger(passenger);
    }

    @Override
    public void setTicksExist(int exist) {
        handle.setTicksLived(exist);
    }

    @Override
    public void sendMessage(String message) {
        handle.sendMessage(message);
    }

    @Override
    public void sendMessages(String... message) {
        Arrays.stream(message).forEach(this::sendMessage);
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return handle.hasPermission(permission);
    }

    @Override
    public JsonObject getNBT() {
        return NBTUtils.nbtToJson(NBTUtils.getEntityNbt(handle));
    }

    @Override
    public void updateNBT(JsonObject nbt) {
        NBTUtils.setEntityNbt(handle, NBTUtils.jsonToNbt(nbt));
    }

    @Override
    public IPosition getPosition() {
        return Server.getInstance().getWorldManager().getWorld(handle.getWorld().getName()).positionRotated(handle.getLocation().getX(), handle.getLocation().getY(), handle.getLocation().getZ(), handle.getLocation().getYaw(), handle.getLocation().getPitch());
    }

    @Override
    public boolean setPosition(IPosition position) {
        return handle.teleport(new Location(Bukkit.getWorld(position.getWorld()), position.getX(), position.getY(), position.getZ(), position.getYaw(), position.getPitch()));
    }
}