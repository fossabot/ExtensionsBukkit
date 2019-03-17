package ml.extbukkit.main.secure.world.entity;

import com.google.gson.JsonObject;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.world.IPosition;
import ml.extbukkit.api.world.StraightDirection;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.secure.command.CommandExecutor;
import ml.extbukkit.main.secure.nms.NBTUtils;
import ml.extbukkit.main.secure.world.DirectionHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Entity extends CommandExecutor implements IEntity {

    private org.bukkit.entity.Entity base;

    public Entity(org.bukkit.entity.Entity handle) {
        super(handle);
        this.base = handle;
    }

    @Override
    public boolean addPassenger(IEntity passenger) {
        return base.addPassenger(((Entity) passenger).base);
    }

    @Override
    public boolean ejectPassengers() {
        return base.eject();
    }

    @Override
    public void getBoundingBox() {
        // TODO
    }

    @Override
    public int getId() {
        return base.getEntityId();
    }

    @Override
    public StraightDirection getDirection() {
        return DirectionHelper.bukkitToStraight(base.getFacing());
    }

    @Override
    public double getHeight() {
        return base.getHeight();
    }

    @Override
    public void getDamageEvent() {
        // TODO
    }

    @Override
    public List<IEntity> getPassengers() {
        List<IEntity> ents = new ArrayList<>();
        for (org.bukkit.entity.Entity ent : base.getPassengers()) {
            ents.add(new Entity(ent));
        }
        return ents;
    }

    @Override
    public int getTicksExist() {
        return base.getTicksLived();
    }

    @Override
    public IEntityType getType() {
        return EntityHelper.bukkitToEB(base.getType());
    }

    @Override
    public UUID getUUID() {
        return base.getUniqueId();
    }

    @Override
    public IEntity getPassengerOf() {
        return new Entity(base.getVehicle());
    }

    @Override
    public double getWidth() {
        return base.getWidth();
    }

    @Override
    public boolean isMarkedForRemoval() {
        return base.isDead();
    }

    @Override
    public boolean hasPassengers() {
        return !base.getPassengers().isEmpty();
    }

    @Override
    public boolean isPassenger() {
        return base.isInsideVehicle();
    }

    @Override
    public boolean isExist() {
        return base.isValid();
    }

    @Override
    public boolean ejectAsPassenger() {
        return base.leaveVehicle();
    }

    @Override
    public void remove() {
        base.remove();
    }

    @Override
    public boolean removePassenger(IEntity passenger) {
        return base.removePassenger(((Entity) passenger).base);
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
        base.setTicksLived(exist);
    }

    @Override
    public JsonObject getNBT() {
        return NBTUtils.nbtToJson(NBTUtils.getEntityNbt(base));
    }

    @Override
    public void updateNBT(JsonObject nbt) {
        NBTUtils.setEntityNbt(base, NBTUtils.jsonToNbt(nbt));
    }

    @Override
    public IPosition getPosition() {
        return IServer.getInstance().getWorldManager().getWorld(base.getWorld().getName()).positionRotated(base.getLocation().getX(), base.getLocation().getY(), base.getLocation().getZ(), base.getLocation().getYaw(), base.getLocation().getPitch());
    }

    @Override
    public boolean setPosition(IPosition position) {
        return base.teleport(new Location(Bukkit.getWorld(position.getWorld()), position.getX(), position.getY(), position.getZ(), position.getYaw(), position.getPitch()));
    }
}