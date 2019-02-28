package ml.extbukkit.main.secure.world.entity;

import com.google.gson.JsonObject;
import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.ChatMessageSerializer;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.util.AWrapper;
import ml.extbukkit.api.world.IPosition;
import ml.extbukkit.api.world.StraightDirection;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.secure.nms.NBTUtils;
import ml.extbukkit.main.secure.server.Server;
import ml.extbukkit.main.secure.world.DirectionHelper;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Entity extends AWrapper<org.bukkit.entity.Entity> implements IEntity {

    public Entity(org.bukkit.entity.Entity handle) {
        super(handle);

    }

    @Override
    public boolean addPassenger(IEntity passenger) {
        return handle.addPassenger(((Entity) passenger).handle);
    }

    @Override
    public boolean ejectPassengers() {
        return handle.eject();
    }

    @Override
    public void getBoundingBox() {
        // TODO
    }

    @Override
    public int getId() {
        return handle.getEntityId();
    }

    @Override
    public StraightDirection getStraightDirection() {
        return DirectionHelper.bukkitToStraight(handle.getFacing());
    }

    @Override
    public void getDirection() {
        // TODO
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
        List<IEntity> ents = new ArrayList<>();
        for (org.bukkit.entity.Entity ent : handle.getPassengers())
            ents.add(new Entity(ent));
        return ents;
    }

    @Override
    public int getTicksExist() {
        return handle.getTicksLived();
    }

    @Override
    public IEntityType getType() {
        return EntityHelper.bukkitToEB(handle.getType());
    }

    @Override
    public UUID getUUID() {
        return handle.getUniqueId();
    }

    @Override
    public IEntity getPassengerOf() {
        return new Entity(handle.getVehicle());
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
        return handle.removePassenger(((Entity) passenger).handle);
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
    public void executeCommand(String command) {
        // TODO
    }

    @Override
    public void sendMessage(ChatMessage message) {
        handle.spigot().sendMessage( ComponentSerializer.parse( ChatMessageSerializer.getInstance().toString( message ) ) );
    }

    @Override
    public String getName()
    {
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